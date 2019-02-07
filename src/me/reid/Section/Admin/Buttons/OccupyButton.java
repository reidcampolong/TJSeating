package me.reid.Section.Admin.Buttons;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import me.reid.Client;
import me.reid.Database.Database;

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
        Client.getDatabase().saveDayToDoc(Database.currentDay);
    }


}
