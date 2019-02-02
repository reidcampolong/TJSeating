package me.reid.Section.Admin;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class GroupButton extends AdminButton {

    private String colorString = "ffd700";
    private Button physicalButton;

    public GroupButton(String buttonTitle) {
        super(buttonTitle);
    }

    @Override
    public void handle(ActionEvent event) {
        if(isSelected()) {
            finalizeGroupSelect();
        }else
            setSelected(true);
        updateStyle();
    }

    public void finalizeGroupSelect() {
        GroupClickHandler.getGroupSeatList();
        setSelected(false);
    }

}
