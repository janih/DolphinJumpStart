package step_0;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class JumpStart extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Dolphin Jump Start");
        Pane root = new Pane();

        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.setTitle(getClass().getName());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(JumpStart.class);
    }
}
