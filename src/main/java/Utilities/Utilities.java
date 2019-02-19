package main.java.Utilities;

import javafx.scene.control.Alert;
import main.java.Section.Seat.SeatHandler;

/**
 * Created by Reid on 2/18/19.
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
