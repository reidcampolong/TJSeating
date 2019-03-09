package main.java.Section.Admin.Buttons;

import javafx.event.ActionEvent;
import main.java.Client;

import java.util.List;

/**
 * An admin button that changes the database view
 */
public class DayButton extends AdminButton {

    private final List<DayButton> dayButtons;
    private String databaseTable;

    public DayButton(String databaseTable, List<DayButton> dayButtons) {
        super(databaseTable.toUpperCase()); // Set day button's title
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
