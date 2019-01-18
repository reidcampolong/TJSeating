package me.reid.Section;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import me.reid.Utilities.SeatTranslator;

/**
 * A section is a collection of seats with a set row and column count
 */
public class Section {

    private GridPane gridPane;
    private Button[][] buttons;

    public Section(int cols, int rows) {
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        this.initPane(cols, rows);
    }

    private void initPane(int cols, int rows) {
        buttons = new Button[cols][rows];
        for (int col = 0; col < buttons.length; col++) {
            for (int row = 0; row < buttons[col].length; row++) {

                Button button = new Button(SeatTranslator.getName(col, row));
                button.getProperties().put("pos", new int[]{col, row});
                buttons[col][row] = button;

                button.setPrefWidth(55);
                button.setPrefHeight(15);
                GridPane.setConstraints(button, row, col);

                gridPane.getChildren().add(button);
                button.setOnMouseClicked(event ->
                {
                    int[] properties = (int[]) button.getProperties().get("pos");
                    System.out.println(SeatTranslator.getName(properties[0], properties[1]));
                });
            }

        }
    }

    public GridPane getGridPane() {
        return gridPane;
    }


}
