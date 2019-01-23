package me.reid.Section;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 * Created by Reid on 1/23/19.
 */
public class AdminSection {

    private GridPane gridPane;

    public AdminSection() {
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.getChildren().add(new Button("Admin"));
    }

    public GridPane getGridPane() {
        return gridPane;
    }
}
