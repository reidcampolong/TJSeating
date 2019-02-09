package main.java.Section;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import java.util.HashMap;

/**
 * Created by Reid on 2/4/19.
 */
public class SectionHandler {

    public static int[] sectionAValues = {0, 31, 7};
    public static int[] sectionBValues = {1, 29, 14};
    public static int[] sectionCValues = {2, 31, 7};

    private HBox horozontalContainer;
    private HashMap<Integer, Section> sectionMap;
    public SectionHandler() {
        horozontalContainer = new HBox();
        horozontalContainer.setAlignment(Pos.CENTER);
        sectionMap = new HashMap<>();
    }

    public void addSection(int sectionID, Section section ) {
        sectionMap.put(sectionID, section);
    }

    public Section getSection(int sectionID) {
        return sectionMap.get(sectionID);
    }

    public void updateSectionsToGrid(Section left, Section mid, Section right) {
        sectionMap.clear();
        addSection(0, left);
        addSection(1, mid);
        addSection(2, right);
        horozontalContainer.getChildren().clear();
        horozontalContainer.getChildren().addAll(left.getGridPane(), mid.getGridPane(), right.getGridPane());
    }

    public HBox getHorozontalContainer () {
        return horozontalContainer;
    }
}
