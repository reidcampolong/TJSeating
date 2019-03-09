package main.java.Utilities;

import javafx.scene.control.Alert;
import main.java.Section.Seat.SeatHandler;

/**
 * Utilities class that allows for creating popups
 */
public class Utilities {

    public static void createNicePopup(String title, String message) {
        // Loading data dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setGraphic(SeatHandler.dataLogo);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}
