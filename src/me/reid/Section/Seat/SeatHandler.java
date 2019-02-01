package me.reid.Section.Seat;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import me.reid.Utilities.StringUtils;

import java.util.Optional;

/**
 * Created by Reid on 1/31/19.
 */
public class SeatHandler {

    /**
     * Updates the seat's holder and status
     * @param seat
     * @param newStatus
     * @param holder
     */
    public static void updateSeat(Seat seat, Status newStatus, String holder) {
        seat.changeStatus(newStatus);
        seat.changeSeatHolder(holder);
    }

    /**
     * Creates a popup and allows for the changing of a seat's status
     *
     * @param seat
     */
    public static void handleInputForSeat(Seat seat) {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Seat Editor");
        dialog.setHeaderText("Seat " + seat.getSectionTitle() + "\n" + "Holder: " + seat.getSeatHolder() + "\n" + "Status: " + seat.getSeatStatus());

        ButtonType finishButton = new ButtonType("Finish", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(finishButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        ComboBox statusSelector = new ComboBox();

        if (seat.getSeatStatus() == Status.AVAILABLE)
            statusSelector.setValue("Occupied");
        else
            statusSelector.setValue(StringUtils.properCase(seat.getSeatStatus().toString()));
        statusSelector.getItems().addAll("Occupied", "Available", "Black", "Handicap");

        TextField seatHolder = new TextField();
        seatHolder.setPromptText("Customer Name");
        if (!seat.getSeatHolder().equals("None"))
            seatHolder.setText(seat.getSeatHolder());

        grid.add(new Label("Status:"), 0, 0);
        grid.add(statusSelector, 1, 0);
        grid.add(new Label("Holder:"), 0, 1);
        grid.add(seatHolder, 1, 1);


        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == finishButton) {
                return new Pair<>(statusSelector.getValue().toString(), seatHolder.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        if (result.isPresent()) {

            // Update seat status
            Status newStatus = Status.valueOf(result.get().getKey().toUpperCase());

            if (newStatus == Status.OCCUPIED) {
                String holder = result.get().getValue();
                if (holder.trim().equals("")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Seat Editor");
                    alert.setHeaderText(null);
                    alert.setContentText("You must specify a customer's name!");

                    alert.showAndWait();
                    handleInputForSeat(seat);
                    return;
                } else {
                    updateSeat(seat, newStatus, holder);
                }
            } else
                updateSeat(seat, newStatus, "None");
        }
    }
}
