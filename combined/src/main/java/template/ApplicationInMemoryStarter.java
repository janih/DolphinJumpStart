package template;

import org.opendolphin.core.client.ClientDolphin;
import org.opendolphin.core.client.ClientModelStore;
import org.opendolphin.core.client.comm.InMemoryClientConnector;
import org.opendolphin.core.client.comm.JavaFXUiThreadHandler;
import org.opendolphin.core.server.ServerDolphin;

public class ApplicationInMemoryStarter {
	public static void main(String[] args) throws Exception {
		ClientDolphin clientDolphin = new ClientDolphin();
		clientDolphin.setClientModelStore(new ClientModelStore(clientDolphin));
		InMemoryClientConnector connector = new InMemoryClientConnector(clientDolphin);
		connector.setUiThreadHandler(new JavaFXUiThreadHandler());
		clientDolphin.setClientConnector(connector);

		template.Application.clientDolphin = clientDolphin;
		javafx.application.Application.launch(template.Application.class);
	}

	private static void registerApplicationActions(ServerDolphin config) {
		config.	register(new ApplicationDirector());
	}

}
