package me.reid;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Client extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TJ Seating by Reid Campolong");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setVgap(8);

        Button[] buttons = new Button[10];
        int x = 0;
        int y  = 0;
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button("A" + i);

            GridPane.setConstraints(buttons[i], x, y);
            gridPane.getChildren().add(buttons[i]);

            x++;
            if(x % 3 == 0) {
                y += 1;
                x = 0;
            }
        }

        Scene scene = new Scene(gridPane,500,500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
