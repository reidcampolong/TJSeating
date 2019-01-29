package me.reid.Section.Admin;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import me.reid.Section.Seat.Status;

/**
 * Created by Reid on 1/24/19.
 */
public class OccupyButton extends AdminButton {

    private String colorString = "ffd700";
    private Button physicalButton;

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
    }


}
