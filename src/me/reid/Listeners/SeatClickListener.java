package me.reid.Listeners;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import me.reid.Section.Seat.Seat;
import me.reid.Section.Seat.Status;
import me.reid.Utilities.StringUtils;

import java.util.Optional;

/**
 * A listener for each seat button
 */
public class SeatClickListener implements EventHandler<ActionEvent> {

    private Seat seat;

    public SeatClickListener(Seat seat) {
        this.seat = seat;
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println(seat.getX() + "&" + seat.getY());
        handleInputForSeat(seat);
    }

    public void handleInputForSeat(Seat seat) {
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

            if(result.get().getValue().trim().equals("")) {


            }
            // Update seat status
            Status newStatus = Status.valueOf(result.get().getKey().toUpperCase());

            if (newStatus == Status.OCCUPIED) {
                String holder = result.get().getValue();
                if(holder.trim().equals("")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Seat Editor");
                    alert.setHeaderText(null);
                    alert.setContentText("You must specify a customer's name!");

                    alert.showAndWait();
                    handleInputForSeat(seat);
                    return;
                } else {
                    this.seat.changeSeatHolder(holder);
                }
            } else
                this.seat.changeSeatHolder("None");
            seat.changeStatus(newStatus);
        }
    }

    public Status askForStatus() {
        return null;
    }

    public String askForCustomer() {
        TextInputDialog dialog = new TextInputDialog("Full Name");
        dialog.setTitle("TJ Seating");
        //dialog.setHeaderText("Seat " + seat.get);
        dialog.setContentText("Please enter customer's name:");

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            System.out.println("Your name: " + result.get());
        }
        return null;
    }
}
