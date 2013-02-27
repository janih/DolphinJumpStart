package step_7;

import javafx.application.Application;
import org.opendolphin.core.client.ClientDolphin;
import org.opendolphin.core.client.ClientModelStore;
import org.opendolphin.core.client.comm.HttpClientConnector;
import org.opendolphin.core.client.comm.JavaFXUiThreadHandler;
import org.opendolphin.core.comm.JsonCodec;

public class TutorialStarter {

    public static void main(String[] args) throws Exception {
        ClientDolphin clientDolphin = new ClientDolphin();
        clientDolphin.setClientModelStore(new ClientModelStore(clientDolphin));
        HttpClientConnector connector = new HttpClientConnector(clientDolphin, "http://localhost:8080/myFirstDolphin");
        connector.setCodec(new JsonCodec());
        connector.setUiThreadHandler(new JavaFXUiThreadHandler());
        clientDolphin.setClientConnector(connector);

        TutorialApplication.clientDolphin = clientDolphin;
        Application.launch(TutorialApplication.class);
    }
}
