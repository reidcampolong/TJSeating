package me.reid;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.reid.Section.Section;

public class Client extends Application {

    private static final String VERSION = ".5";

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TJ Seating by Reid Campolong v" + VERSION);

        Section leftSection = new Section(26, 7);

        Scene scene = new Scene(leftSection.getGridPane(), 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
