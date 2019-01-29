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
        seat.changeStatus(Status.BLACK);

        /*List<String> choices = new ArrayList<>();
        choices.add("Occupy");
        choices.add("Available");
        choices.add("Black-Out");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Occupy", choices);
        dialog.setTitle("TJSeating - Reid C");
        dialog.setHeaderText("Seat: " + seat.getSectionTitle());
        dialog.setContentText("Choose your letter:");

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            System.out.println("Your choice: " + result.get());
        }

// The Java 8 way to get the response value (with lambda expression).
        result.ifPresent(letter -> System.out.println("Your choice: " + letter));*/
        handleInputForSeats(seat);

    }

    public void handleInputForSeats(Seat... seat) {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Login Dialog");
        dialog.setHeaderText("Look, a Custom Login Dialog");

        ButtonType finishButton = new ButtonType("Finish", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(finishButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        ComboBox statusSelector = new ComboBox();
        statusSelector.setValue("Occupied");
        statusSelector.getItems().addAll("Occupied", "Available", "Black");

        TextField seatHolder = new TextField();
        seatHolder.setPromptText("Customer Name");

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
        if(result.isPresent()) {
            this.seat.changeStatus(Status.valueOf(result.get().getKey().toUpperCase()));
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
