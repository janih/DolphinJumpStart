package step_3;

import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckBoxBuilder;
import javafx.scene.control.LabelBuilder;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFieldBuilder;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.Pane;
import javafx.scene.layout.PaneBuilder;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;

import org.opendolphin.binding.JFXBinder;
import org.opendolphin.core.PresentationModel;
import org.opendolphin.core.client.ClientAttribute;
import org.opendolphin.core.client.ClientDolphin;
import org.opendolphin.core.client.ClientModelStore;
import org.opendolphin.core.client.ClientPresentationModel;
import org.opendolphin.core.client.comm.InMemoryClientConnector;
import org.opendolphin.core.client.comm.JavaFXUiThreadHandler;
import org.opendolphin.core.client.comm.OnFinishedHandlerAdapter;
import org.opendolphin.core.comm.Command;
import org.opendolphin.core.comm.NamedCommand;
import org.opendolphin.core.server.ServerDolphin;
import org.opendolphin.core.server.comm.NamedCommandHandler;

public class JumpStart extends Application {

	private static final String MODEL_ID = "modelId";
	private static final String MODEL_ATTRIBUTE_ID = "attrId";
	private static final String COMMAND_ID = "LogOnServer";

	ClientDolphin clientDolphin = new ClientDolphin();
	{
		clientDolphin.setClientModelStore(new ClientModelStore(clientDolphin));
		InMemoryClientConnector connector = new InMemoryClientConnector(clientDolphin);
		connector.setUiThreadHandler(new JavaFXUiThreadHandler());
		clientDolphin.setClientConnector(connector);
	}
	ServerDolphin serverDolphin = new ServerDolphin();
	private TextField textField;
	private Button button;
	private CheckBox status;
	private final PresentationModel textAttributeModel;

	public JumpStart() {
		textAttributeModel = clientDolphin.presentationModel(MODEL_ID, new ClientAttribute(MODEL_ATTRIBUTE_ID, ""));
	}

	@Override
	public void start(Stage stage) throws Exception {
		Pane root = PaneBuilder.create().children(
				VBoxBuilder.create().children(
						textField = TextFieldBuilder.create().build(),
						button = ButtonBuilder.create().text("press me").build(),
						HBoxBuilder.create().children(
								LabelBuilder.create().text("IsDirty ?").build(),
								status = CheckBoxBuilder.create().disable(true).build()
								).build()

						).build()
				).build();

		addServerSideAction();
		addClientSideAction();
		setupBinding();

		stage.setScene(new Scene(root, 300, 100));
		stage.show();
	}

	private void setupBinding() {
		JFXBinder.bind("text").of(textField).to(MODEL_ATTRIBUTE_ID).of(textAttributeModel);
		JFXBinder.bindInfo("dirty").of(textAttributeModel.getAt(MODEL_ATTRIBUTE_ID)).to("selected").of(status);
	}

	private void addClientSideAction() {
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				clientDolphin.send(COMMAND_ID, new OnFinishedHandlerAdapter() {
					@Override
					public void onFinished(List<ClientPresentationModel> presentationModels) {
						textAttributeModel.getAt(MODEL_ATTRIBUTE_ID).rebase();
					}
				});
			}
		});
	}

	private void addServerSideAction() {
		serverDolphin.action(COMMAND_ID, new NamedCommandHandler() {
			@Override
			public void handleCommand(NamedCommand command, List<Command> response) {
				System.out.println(serverDolphin.getAt(MODEL_ID).getAt(MODEL_ATTRIBUTE_ID).getValue());
			}
		});
	}

	public static void main(String[] args) {
		launch(JumpStart.class);
	}
}
