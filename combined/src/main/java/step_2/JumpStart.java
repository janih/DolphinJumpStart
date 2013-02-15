package step_2;

import com.canoo.dolphin.binding.JFXBinder;
import com.canoo.dolphin.core.PresentationModel;
import com.canoo.dolphin.core.client.ClientAttribute;
import com.canoo.dolphin.core.client.ClientDolphin;
import com.canoo.dolphin.core.client.comm.JavaFXUiThreadHandler;
import com.canoo.dolphin.core.comm.Command;
import com.canoo.dolphin.core.comm.DefaultInMemoryConfig;
import com.canoo.dolphin.core.comm.NamedCommand;
import com.canoo.dolphin.core.server.ServerDolphin;
import com.canoo.dolphin.core.server.action.DolphinServerAction;
import com.canoo.dolphin.core.server.comm.ActionRegistry;
import com.canoo.dolphin.core.server.comm.CommandHandler;
import com.canoo.dolphin.core.server.comm.NamedCommandHandler;
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

import java.util.List;

public class JumpStart extends Application {

    private TextField field;
    private Button button;
    private DefaultInMemoryConfig config = new DefaultInMemoryConfig();
    ClientDolphin clientDolphin = config.getClientDolphin();
    ServerDolphin serverDolphin = config.getServerDolphin();

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
        config.registerDefaultActions();

        PresentationModel input = clientDolphin.presentationModel("input", new ClientAttribute("text"));

        JFXBinder.bind("text").of(field).to("text").of(input);

        config.getServerDolphin().action("PrintText", new NamedCommandHandler() {
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
            public void handle(ActionEvent actionEvent) {
                clientDolphin.send("PrintText");
            }
        });
    }

    public static void main(String[] args) {
        launch(JumpStart.class);
    }
}
