package main.java.Section.Admin.Buttons;

import javafx.event.ActionEvent;
import main.java.Client;

/**
 * Created by Reid on 1/24/19.
 */
public class SaveALLButton extends AdminButton {

    public SaveALLButton(String buttonTitle) {
        super(buttonTitle);
    }

    @Override
    public void handle(ActionEvent event) {
        Client.getDatabase().saveDayToDoc("friday");
    }


}
