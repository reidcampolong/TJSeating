package me.reid.Section.Admin;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import me.reid.Section.Admin.Buttons.AdminButton;
import me.reid.Section.Admin.Buttons.DayButton;
import me.reid.Section.Admin.Buttons.GroupButton;
import me.reid.Section.Admin.Buttons.OccupyButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains buttons used only by admins
 */
public class AdminSection {

    private HBox hBox;

    private AdminButton groupButton;

    private List<DayButton> dayButtons;
    private DayButton thursdayButton;
    private DayButton fridayButton;
    private DayButton satMatButton;
    private DayButton satNightButton;

    public AdminSection() {

        dayButtons = new ArrayList<>(4);
        hBox = new HBox(5);
        hBox.setPadding(new Insets(2, 10, 2, 10));

        groupButton = new GroupButton("Group");
        new GroupClickHandler((GroupButton) groupButton);

        AdminButton saveButton = new OccupyButton("Save ALL");
        thursdayButton = new DayButton("thursday", dayButtons);
        dayButtons.add(thursdayButton);
        fridayButton = new DayButton("friday", dayButtons);
        dayButtons.add(fridayButton);
        satMatButton = new DayButton("satmat", dayButtons);
        dayButtons.add(satMatButton);
        satNightButton = new DayButton("satnight", dayButtons);
        dayButtons.add(satNightButton);

        hBox.getChildren().add(saveButton.getPhysicalButton());
        hBox.getChildren().add(groupButton.getPhysicalButton());
        for(DayButton b : dayButtons)
            hBox.getChildren().add(b.getPhysicalButton());
        //gridPane.getChildren().addAll(groupButton.getPhysicalButton(), occupyButton.getPhysicalButton());
    }

    public HBox getGridPane() {
        return hBox;
    }
}
