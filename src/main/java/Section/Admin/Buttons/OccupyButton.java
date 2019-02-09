package main.java.Section.Admin.Buttons;

import javafx.event.ActionEvent;
import main.java.Client;
import main.java.Database.Database;

/**
 * Created by Reid on 1/24/19.
 */
public class OccupyButton extends AdminButton {

    public OccupyButton(String buttonTitle) {
        super(buttonTitle);
    }

    @Override
    public void handle(ActionEvent event) {
        if(isSelected())
            setSelected(false);
        else
            setSelected(true);
        updateStyle();
        //Client.getDatabase().saveDayToDoc(Database.currentDay);
    }


}
