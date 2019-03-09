package main.java.Section.Admin.Buttons;

import javafx.event.ActionEvent;
import main.java.Section.Admin.GroupClickHandler;

/**
 * An admin button that allows to select multiple seats at a time
 */
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
