package me.reid.Section.Admin.Buttons;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import me.reid.Client;

import java.util.List;

public class DayButton extends AdminButton {

    private List<DayButton> dayButtons;
    private String databaseTable;

    public DayButton(String databaseTable, List<DayButton> dayButtons) {
        super(databaseTable.toUpperCase());
        this.dayButtons = dayButtons;
        this.databaseTable = databaseTable;
    }

    @Override
    public void handle(ActionEvent event) {
        for(DayButton b : dayButtons)
            b.setSelected(false);
        setSelected(true);
        Client.getDatabase().switchViewTo(databaseTable);
    }
}
