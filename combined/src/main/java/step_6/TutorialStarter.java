package step_6;

import javafx.application.Application;
import org.opendolphin.core.client.comm.JavaFXUiThreadHandler;
import org.opendolphin.core.comm.DefaultInMemoryConfig;

public class TutorialStarter {

    public static void main(String[] args) throws Exception {
        DefaultInMemoryConfig config = new DefaultInMemoryConfig();
        config.registerDefaultActions();
        config.getClientDolphin().getClientConnector().setUiThreadHandler(new JavaFXUiThreadHandler());
        addActions(config);
        TutorialApplication.clientDolphin = config.getClientDolphin();
        Application.launch(TutorialApplication.class);
    }

    private static void addActions(DefaultInMemoryConfig config) {
        config.getServerDolphin().register(new TutorialDirector());
    }

}
