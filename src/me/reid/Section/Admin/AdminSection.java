package me.reid.Section.Admin;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 * Contains buttons used only by admins
 */
public class AdminSection {

    private GridPane gridPane;

    private AdminButton occupyButton;

    public AdminSection() {
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(2, 10, 2, 10));
        occupyButton = new OccupyButton("Occupy");
        gridPane.getChildren().add(occupyButton.getPhysicalButton());
    }

    public GridPane getGridPane() {
        return gridPane;
    }
}
