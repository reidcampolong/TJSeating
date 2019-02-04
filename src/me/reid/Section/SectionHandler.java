package me.reid.Section;

import java.util.HashMap;

/**
 * Created by Reid on 2/4/19.
 */
public class SectionHandler {

    private HashMap<Integer, Section> sectionMap;
    public SectionHandler() {
        sectionMap = new HashMap<>();
    }

    public void addSection(int sectionID, Section section ) {
        sectionMap.put(sectionID, section);
    }

    public Section getSection(int sectionID) {
        return sectionMap.get(sectionID);
    }
}
