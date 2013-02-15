package step_6;

import com.canoo.dolphin.core.client.comm.JavaFXUiThreadHandler;
import com.canoo.dolphin.core.comm.DefaultInMemoryConfig;
import com.canoo.dolphin.core.server.ServerDolphin;
import javafx.application.Application;

import static step_6.TutorialConstants.COMMAND_ID;

public class TutorialStarter {

    private static void addServerSideAction(final ServerDolphin config) {
        config.action(COMMAND_ID, new TutorialAction(config));
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
