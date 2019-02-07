package main.java.Listeners;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import main.java.Section.Seat.Seat;
import main.java.Section.Seat.SeatHandler;

/**
 * A listener for each seat button
 */
public class SeatClickListener implements EventHandler<ActionEvent> {

    private Seat seat;

    public SeatClickListener(Seat seat) {
        this.seat = seat;
    }

    public void handle(ActionEvent event) {
        SeatHandler.handleInputForSeat(seat);
    }

}
