package main.java.Section.Admin.Buttons;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * An admin button that changes color and performs special actionwhen toggled
 */
public abstract class AdminButton implements EventHandler<ActionEvent> {

    private final String unselectedButtonColor = "ffd700";
    private final String selectedButtonColor = "00ff00";

    private Button physicalButton;
    private boolean selected = false;

    public AdminButton(String buttonTitle) {
        initButton(buttonTitle);
    }

    /**
     * Create the button
     * @param buttonTitle
     */
    private void initButton(String buttonTitle) {
        physicalButton = new Button(buttonTitle);
        physicalButton.setPrefWidth(80);
        physicalButton.setPrefHeight(5);
        physicalButton.setOnAction(this);
        updateStyle();
    }

    /**
     * Update the button's color and style
     */
    public void updateStyle() {
        String colorString = (selected) ? selectedButtonColor : unselectedButtonColor;
        this.physicalButton.setStyle("-fx-font-weight: bold; -fx-font-size: 10; -fx-background-color: #" + colorString + ";");
    }

    /**
     * Handle button's special action
     * @param event
     */
    public abstract void handle(ActionEvent event);

    /**
     * Change whether the button is selected and update style
     * @param value
     */
    public void setSelected(boolean value) {
        this.selected = value;
        updateStyle();
    }

    public Button getPhysicalButton() {
        return physicalButton;
    }

    public boolean isSelected() {
        return selected;
    }



}
