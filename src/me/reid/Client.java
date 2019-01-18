package me.reid;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import me.reid.Section.Section;

public class Client extends Application {

    private static final String VERSION = ".5";

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TJ Seating by Reid Campolong v" + VERSION);

        Section leftSection = new Section(26, 7);
        Section middleSection = new Section(26, 7);
        Section rightSection = new Section(26, 7);

        leftSection.getGridPane().setAlignment(Pos.CENTER);
        middleSection.getGridPane().setAlignment(Pos.CENTER);
        rightSection.getGridPane().setAlignment(Pos.CENTER);

        HBox box = new HBox();
        box.getChildren().addAll(leftSection.getGridPane(), middleSection.getGridPane(), rightSection.getGridPane());
        box.setAlignment(Pos.CENTER);

        Scene scene = new Scene(box, 1300, 850);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
