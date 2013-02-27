package step_5;

import org.opendolphin.core.client.comm.JavaFXUiThreadHandler;
import org.opendolphin.core.comm.DefaultInMemoryConfig;
import org.opendolphin.core.server.ServerDolphin;
import javafx.application.Application;

import static step_5.TutorialConstants.CMD_LOG;

public class TutorialStarter {

    private static void addServerSideAction(final ServerDolphin config) {
        config.action(CMD_LOG, new TutorialAction(config));
    }

    public static void main(String[] args) throws Exception {
        DefaultInMemoryConfig config = new DefaultInMemoryConfig();
        config.registerDefaultActions();
        config.getClientDolphin().getClientConnector().setUiThreadHandler(new JavaFXUiThreadHandler());
        addServerSideAction(config.getServerDolphin());
        TutorialApplication.clientDolphin = config.getClientDolphin();
        Application.launch(TutorialApplication.class);
    }

}
