package me.reid.Section.Admin.Buttons;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import me.reid.Section.Admin.GroupClickHandler;

public class GroupButton extends AdminButton {

    public GroupButton(String buttonTitle) {
        super(buttonTitle);
    }

    @Override
    public void handle(ActionEvent event) {
        if (isSelected()) {
            GroupClickHandler.i().toggleGroupSelect(false);
            setSelected(false);
        } else {
            GroupClickHandler.i().toggleGroupSelect(true);
            setSelected(true);
        }
        updateStyle();
    }

    public void finalizeGroupSelect() {

        setSelected(false);
    }

}
