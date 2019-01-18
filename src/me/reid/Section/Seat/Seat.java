package me.reid.Section.Seat;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import me.reid.Listeners.SeatClickListener;
import me.reid.Section.Section;
import me.reid.Utilities.SeatTranslator;

public class Seat {

    private Section section;
    private int x, y;
    private Button clientButton;

    private Status seatStatus;
    private String seatHolder;

    public Seat(Section section, int y, int x, String seatHolder, Status seatStatus) {
        this.section = section;
        this.x = x;
        this.y = y;
        this.seatHolder = seatHolder;
        this.seatStatus = seatStatus;

        createButton();
    }

    public void changeStatus(Status newStatus) {
        this.seatStatus = newStatus;
        updateSeatStyle();
    }

    private void updateSeatStyle() {
        String colorString = "";
        switch(seatStatus) {
            case AVAILABLE:
                colorString = "00ff00";
                break;
            case OCCUPIED:
                colorString = "ff0000";
                break;
            case BLACK:
                colorString = "6d6d6d";
                break;
            case HANDICAP:
                colorString = "0085FF";
                break;
        }
        clientButton.setStyle("-fx-font-weight: bold; -fx-font-size: 13; -fx-background-color: #"+colorString+"; -fx-border-color: #000000; -fx-border-width: 1px;");
    }

    private void createButton() {

        clientButton = new Button(SeatTranslator.getName(y, x));
        clientButton.setPrefWidth(55);
        clientButton.setPrefHeight(10);

        updateSeatStyle();

        clientButton.setOnAction(new SeatClickListener(this));

        GridPane.setConstraints(clientButton, x, y);
    }

    public Button getClientButton() {
        return clientButton;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}