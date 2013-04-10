package step_0;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class JumpStart extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(new Pane(), 300, 100));
        primaryStage.setTitle("Dolphin Jump Start");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(JumpStart.class);
    }
}
