package me.reid.Section.Admin;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Created by Reid on 1/24/19.
 */
public abstract class AdminButton implements EventHandler<ActionEvent> {

    private boolean selected = false;
    private String unselectedButtonColor = "ffd700";
    private String selectedButtonColor = "00ff00";
    private Button physicalButton;

    public AdminButton(String buttonTitle) {
        initButton(buttonTitle);
    }

    private void initButton(String buttonTitle) {
        this.physicalButton = new Button(buttonTitle);
        this.physicalButton.setOnAction(this);
        updateStyle();
    }

    public void updateStyle() {
        String colorString = (selected) ? selectedButtonColor : unselectedButtonColor;
        this.physicalButton.setStyle("-fx-font-weight: bold; -fx-font-size: 13; -fx-background-color: #" + colorString + ";");
    }

    public Button getPhysicalButton() {
        return physicalButton;
    }

    public abstract void handle(ActionEvent event);

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean value) {
        this.selected = value;
    }


}
