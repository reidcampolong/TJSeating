package me.reid.Section.Admin;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

/**
 * Contains buttons used only by admins
 */
public class AdminSection {

    private HBox hBox;

    private AdminButton groupButton;
    private GroupClickHandler groupClickHandler;
    private AdminButton occupyButton;

    public AdminSection() {
        hBox = new HBox(5);
        hBox.setPadding(new Insets(2, 10, 2, 10));

        groupButton = new GroupButton("Group");
        groupClickHandler = new GroupClickHandler((GroupButton) groupButton);
        occupyButton = new OccupyButton("Occupy");

        hBox.getChildren().add(groupButton.getPhysicalButton());
        hBox.getChildren().add(occupyButton.getPhysicalButton());
        //gridPane.getChildren().addAll(groupButton.getPhysicalButton(), occupyButton.getPhysicalButton());
    }

    public HBox getGridPane() {
        return hBox;
    }
}
