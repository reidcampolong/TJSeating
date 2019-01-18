package me.reid.Listeners;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import me.reid.Section.Seat.Seat;
import me.reid.Section.Seat.Status;

public class SeatClickListener implements EventHandler<ActionEvent> {

    private Seat seat;
    public SeatClickListener(Seat seat) {
        this.seat = seat;
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println(seat.getX() + "&"+seat.getY());
        seat.changeStatus(Status.BLACK);
    }
}
