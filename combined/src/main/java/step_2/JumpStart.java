package step_2;

import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFieldBuilder;
import javafx.scene.layout.Pane;
import javafx.scene.layout.PaneBuilder;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;

import org.opendolphin.binding.JFXBinder;
import org.opendolphin.core.PresentationModel;
import org.opendolphin.core.client.ClientAttribute;
import org.opendolphin.core.client.ClientDolphin;
import org.opendolphin.core.client.ClientModelStore;
import org.opendolphin.core.client.comm.InMemoryClientConnector;
import org.opendolphin.core.client.comm.JavaFXUiThreadHandler;
import org.opendolphin.core.comm.Command;
import org.opendolphin.core.comm.NamedCommand;
import org.opendolphin.core.server.ServerDolphin;
import org.opendolphin.core.server.comm.NamedCommandHandler;

public class JumpStart extends Application {

	private TextField field;
	private Button button;
	ClientDolphin clientDolphin = new ClientDolphin();
	{
		clientDolphin.setClientModelStore(new ClientModelStore(clientDolphin));
		InMemoryClientConnector connector = new InMemoryClientConnector(clientDolphin);
		connector.setUiThreadHandler(new JavaFXUiThreadHandler());
		clientDolphin.setClientConnector(connector);
	}
	ServerDolphin serverDolphin = new ServerDolphin();

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane root = PaneBuilder.create().children(
				VBoxBuilder.create().children(
						field = TextFieldBuilder.create().build(),
						button = ButtonBuilder.create()
						.text("click me")
						.build()).build()
				).build();

		clientDolphin.getClientConnector().setUiThreadHandler(new JavaFXUiThreadHandler());
		serverDolphin.registerDefaultActions();

		PresentationModel input = clientDolphin.presentationModel("input", new ClientAttribute("text"));

		JFXBinder.bind("text").of(field).to("text").of(input);

		serverDolphin.action("PrintText", new NamedCommandHandler() {
			@Override
			public void handleCommand(NamedCommand namedCommand, List<Command> commands) {
				Object text = serverDolphin.getAt("input").getAt("text").getValue();
				System.out.println("server text field contains: " + text);
			}
		});

		addActions();

		primaryStage.setScene(new Scene(root, 300, 300));
		primaryStage.setTitle(getClass().getName());
		primaryStage.show();
	}

	private void addActions() {
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				clientDolphin.send("PrintText");
			}
		});
	}

	public static void main(String[] args) {
		launch(JumpStart.class);
	}
}
