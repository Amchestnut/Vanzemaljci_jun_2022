package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.GlavniView;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scena = new Scene(new GlavniView());

        primaryStage.setScene(scena);
        primaryStage.setTitle("Vanzemaljci");
        primaryStage.show();
    }

    // dodao sam ovaj komentar
}
