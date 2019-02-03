package me.reid.Section.Seat;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import me.reid.Listeners.SeatClickListener;
import me.reid.Section.Section;
import me.reid.Utilities.SeatTranslator;

/**
 * A seat object that holds the button
 */
public class Seat {

    private int sectionNumber;
    private String sectionTitle;

    private Section section;
    private int x, y;
    private Button clientButton;

    private Status seatStatus;
    private String seatHolder;

    /**
     * Constructs a new seat
     * @param section
     * @param y - column in section
     * @param x - row in row
     * @param seatHolder
     * @param seatStatus
     */
    public Seat(int sectionNumber, int y, int x, String seatHolder, Status seatStatus) {
        this.sectionNumber = sectionNumber;
        this.x = x;
        this.y = y;
        this.seatHolder = seatHolder;
        this.seatStatus = seatStatus;
        this.sectionTitle = SeatTranslator.getName(y, x);

        createButton();
    }

    /**
     * Changes the seat status and updates the style
     * @param newStatus
     */
    public void changeStatus(Status newStatus) {
        this.seatStatus = newStatus;
        updateSeatStyle();
    }

    /**
     * Changes the seat holder
     * @param newHolder
     */
    public void changeSeatHolder(String newHolder) {
        this.seatHolder = newHolder;
    }

    /**
     * Visually updates a seat's style
     */
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

    /**
     * Initialzes the seat's button
     */
    private void createButton() {

        clientButton = new Button(SeatTranslator.getName(y, x));
        clientButton.setPrefWidth(50);
        clientButton.setPrefHeight(6);

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

    public String getSectionTitle() {
        return sectionTitle;
    }

    public String getSeatHolder() {
        return seatHolder;
    }

    public Status getSeatStatus() {
        return seatStatus;
    }

    public int getSectionNumber() {
        return sectionNumber;
    }

}
