package main.java.Section.Seat;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import main.java.Client;
import main.java.Network.Packet.NetworkSeat;
import main.java.Section.Admin.GroupClickHandler;
import main.java.Utilities.Log;
import main.java.Utilities.StringUtils;

import java.util.List;
import java.util.Optional;

/**
 * Handles creating popups and seat click event logic
 */
public class SeatHandler {

    public static ImageView dataLogo;

    public static Seat getFromNetworkSeat(NetworkSeat seat) {
        return Client.getSectionHandler().getSection(seat.getSectionNumber()).getSeatAtIndex(seat.getY(), seat.getX());
    }

    /**
     * Updates the seat's holder and status
     *
     * @param seat
     * @param newStatus
     * @param holder
     */
    public static void updateSeat(Seat seat, Status newStatus, String holder) {
        Platform.runLater(() -> {
            seat.changeStatus(newStatus);
            seat.changeSeatHolder(holder);
        });
    }

    /**
     * Creates a popup dialog to read input from the customer
     *
     * @param title
     * @param headerText
     * @param defaultStatus
     * @param customerName
     * @return [Status(String), CustomerName(String)]
     */
    public static Optional<Pair<String, String>> createPopupAndGetFeedback(String title, String headerText, String defaultStatus, String customerName) {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText(headerText);
        dialog.setGraphic(dataLogo);

        ButtonType finishButton = new ButtonType("Finish", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(finishButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        ComboBox statusSelector = new ComboBox();

        statusSelector.setValue(defaultStatus);
        statusSelector.getItems().addAll("Occupied", "Available", "Black", "Handicap");

        TextField seatHolder = new TextField();
        seatHolder.setPromptText("Customer Name");
        if (!customerName.equals("None"))
            seatHolder.setText(customerName);

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
        return dialog.showAndWait();
    }

    /**
     * Creates an alert popup with a message
     *
     * @param title
     * @param reason
     */
    public static void createBadPopup(String title, String reason) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(reason);

        alert.showAndWait();
    }

    /**
     * Modify a group of seats
     * @param seatList
     */
    public static void handleInputForGroupSelect(List<Seat> seatList) {
        String headerText = "Modifying " + seatList.size() + " seats";

        for (Seat s : seatList)
            if (s.getSeatStatus() != Status.AVAILABLE) {
                headerText += "\nNot all seats are GREEN! Careful!";
                break;
            }

        String defaultStatus = "Occupied";
        Optional<Pair<String, String>> result = createPopupAndGetFeedback("Seat Editor", headerText, defaultStatus, "");
        if (result.isPresent()) {

            // Update seat status
            Status newStatus = Status.valueOf(result.get().getKey().toUpperCase());

            String holder;
            if (newStatus == Status.OCCUPIED) {
                holder = result.get().getValue();
                if (holder.trim().equals("")) {
                    createBadPopup("Seat Editor", "You must enter a customers name!");
                    handleInputForGroupSelect(seatList);
                    return;
                }
            } else {
                holder = "None";
            }

            GroupClickHandler.i().removeAll();

            for (Seat s : seatList) {
                attemptPurchase(s, newStatus, holder);
            }

        } else {
            GroupClickHandler.i().removeAll();
        }
    }

    /**
     * Creates a popup and allows for the changing of a seat's status
     *
     * @param seat
     */
    public static void handleInputForSeat(Seat seat) {
        if (GroupClickHandler.i().isGroupSelectEnabled()) {
            if(!GroupClickHandler.i().isInList(seat)) {
                GroupClickHandler.i().addToList(seat);
            } else {
                GroupClickHandler.i().remFromList(seat);
            }
            return;
        }

        String headerText = "Seat " + seat.getSectionTitle() + "\n" + "Holder: " + seat.getSeatHolder() + "\n" + "Status: " + seat.getSeatStatus();
        String defaultStatus;
        if (seat.getSeatStatus() == Status.AVAILABLE)
            defaultStatus = "Occupied";
        else
            defaultStatus = StringUtils.properCase(seat.getSeatStatus().toString());

        Optional<Pair<String, String>> result = createPopupAndGetFeedback("Seat Editor", headerText, defaultStatus, seat.getSeatHolder());
        if (result.isPresent()) {

            // Update seat status
            Status newStatus = Status.valueOf(result.get().getKey().toUpperCase());

            String holder;
            if (newStatus == Status.OCCUPIED) {
                holder = result.get().getValue();
                if (holder.trim().equals("")) {
                    createBadPopup("Seat Editor", "You must enter a customers name!");
                    handleInputForSeat(seat);
                    return;
                }
            } else {
                holder = "None";
            }

            attemptPurchase(seat, newStatus, holder);
        }
    }

    /**
     * Write out changes to database and save them
     * @param seat
     * @param newStatus
     * @param holder
     */
    private static void attemptPurchase(Seat seat, Status newStatus, String holder) {
        Log.i("Writing out seat...");
        Client.getDatabase().writeSeatToDoc(seat, newStatus.toString(), holder);
    }
}
