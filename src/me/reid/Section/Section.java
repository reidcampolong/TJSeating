package me.reid.Section;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import me.reid.Section.Seat.Seat;
import me.reid.Section.Seat.Status;
import me.reid.Utilities.SeatTranslator;

/**
 * A section is a collection of seats with a set row and column count
 */
public class Section {

    private GridPane gridPane;
    private Seat[][] seats;

    public Section(int cols, int rows) {
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        this.initPane(cols, rows);
    }

    private void initPane(int cols, int rows) {
        seats = new Seat[cols][rows];
        for (int col = 0; col < seats.length; col++) {
            for (int row = 0; row < seats[col].length; row++) {

                // Create seat
                Seat seat = new Seat(this, col, row,"None", Status.AVAILABLE);
                seats[col][row] = seat;

                // Add to screen
                gridPane.getChildren().add(seat.getClientButton());

            }

        }
    }

    public GridPane getGridPane() {
        return gridPane;
    }


}
