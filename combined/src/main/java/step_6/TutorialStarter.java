package step_6;

import javafx.application.Application;

import org.opendolphin.core.client.ClientDolphin;
import org.opendolphin.core.client.ClientModelStore;
import org.opendolphin.core.client.comm.InMemoryClientConnector;
import org.opendolphin.core.client.comm.JavaFXUiThreadHandler;
import org.opendolphin.core.server.ServerDolphin;

public class TutorialStarter {

	public static void main(String[] args) throws Exception {
		ClientDolphin clientDolphin = new ClientDolphin();
		clientDolphin.setClientModelStore(new ClientModelStore(clientDolphin));
		InMemoryClientConnector connector = new InMemoryClientConnector(clientDolphin);
		connector.setUiThreadHandler(new JavaFXUiThreadHandler());
		clientDolphin.setClientConnector(connector);
		TutorialApplication.clientDolphin = clientDolphin;

		Application.launch(TutorialApplication.class);
	}

	private static void registerApplicationActions(ServerDolphin config) {
		config.register(new TutorialDirector());
	}

}
