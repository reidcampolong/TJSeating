package me.reid.Section.Admin;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 * Contains buttons used only by admins
 */
public class AdminSection {

    private GridPane gridPane;

    private AdminButton groupButton;
    private GroupClickHandler groupClickHandler;
    private AdminButton occupyButton;

    public AdminSection() {
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(2, 10, 2, 10));

        groupButton = new GroupButton("Group Select");
        groupClickHandler = new GroupClickHandler((GroupButton) groupButton);
        occupyButton = new OccupyButton("Occupy");

        gridPane.getChildren().addAll(groupButton.getPhysicalButton(), occupyButton.getPhysicalButton());
    }

    public GridPane getGridPane() {
        return gridPane;
    }
}
