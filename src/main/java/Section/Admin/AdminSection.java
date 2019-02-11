package main.java.Section.Admin;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import main.java.Section.Admin.Buttons.AdminButton;
import main.java.Section.Admin.Buttons.DayButton;
import main.java.Section.Admin.Buttons.GroupButton;
import main.java.Section.Admin.Buttons.OccupyButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains buttons used only by admins
 */
public class AdminSection {

    private HBox hBox;

    private List<DayButton> dayButtons;
    private GroupClickHandler groupClickHandler;

    public AdminSection() {

        hBox = new HBox(5);
        hBox.setPadding(new Insets(20, 10, 2, 10));
        dayButtons = new ArrayList<>(4);

        GroupButton groupButton = new GroupButton("Group");
        initializeGroupClickHandler(groupButton);

        AdminButton saveButton = new OccupyButton("Save ALL");
        dayButtons.add(new DayButton("thursday", dayButtons));
        dayButtons.add(new DayButton("friday", dayButtons));
        dayButtons.add(new DayButton("satmat", dayButtons));
        dayButtons.add(new DayButton("satnight", dayButtons));

        hBox.getChildren().add(saveButton.getPhysicalButton());
        hBox.getChildren().add(groupButton.getPhysicalButton());
        for(DayButton b : dayButtons)
            hBox.getChildren().add(b.getPhysicalButton());

        // Enable thursday data by default
        dayButtons.get(0).setSelected(true);
    }

    private void initializeGroupClickHandler(GroupButton button) {
        groupClickHandler = new GroupClickHandler(button);
    }

    public HBox getGridPane() {
        return hBox;
    }
}
