package step_5;

import com.canoo.dolphin.binding.JFXBinder;
import com.canoo.dolphin.core.PresentationModel;
import com.canoo.dolphin.core.client.ClientAttribute;
import com.canoo.dolphin.core.client.ClientDolphin;
import com.canoo.dolphin.core.client.ClientPresentationModel;
import com.canoo.dolphin.core.client.comm.OnFinishedHandlerAdapter;
import groovy.lang.Closure;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.Pane;
import javafx.scene.layout.PaneBuilder;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;

import java.util.List;

import static step_5.TutorialConstants.*;

public class TutorialApplication extends Application {



    public static ClientDolphin clientDolphin;

    private TextField textField;
    private Button button;
    private CheckBox status;
    private PresentationModel textAttributeModel;


    public TutorialApplication() {
        textAttributeModel = clientDolphin.presentationModel(PM_PERSON, new ClientAttribute(ATT_FIRSTNAME, ""));
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

        addClientSideAction();
        setupBinding();

        Scene scene = new Scene(root, 600, 300);
        stage.setScene(scene);
        stage.setTitle(getClass().getName());

        stage.show();
    }

    private void setupBinding() {
        JFXBinder.bind("text").of(textField).to(ATT_FIRSTNAME).of(textAttributeModel);
        JFXBinder.bindInfo("dirty").of(textAttributeModel).to("selected").of(status);
        JFXBinder.bindInfo("dirty").of(textAttributeModel).to("disabled").of(button, new Closure(null) {
            protected Object doCall(boolean dirtyState) {
                return !dirtyState;
            }
        });
    }

    private void addClientSideAction() {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                clientDolphin.send(CMD_LOG, new OnFinishedHandlerAdapter() {
                    @Override
                    public void onFinished(List<ClientPresentationModel> presentationModels) {
                        textAttributeModel.getAt(ATT_FIRSTNAME).rebase();
                    }
                });
            }
        });
    }
}
