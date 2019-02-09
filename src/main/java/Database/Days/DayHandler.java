package main.java.Database.Days;

import com.google.cloud.firestore.Firestore;
import main.java.Client;
import main.java.Database.Database;
import main.java.Section.SectionHandler;

import java.util.HashMap;

public class DayHandler {

    private Day currentDay;
    private HashMap<String, Day> dayHashMap;

    public DayHandler(Firestore db) {
        dayHashMap = new HashMap<>(4);
        dayHashMap.put("thursday", new Day(db, "thursday"));
        dayHashMap.put("friday", new Day(db, "friday"));
        dayHashMap.put("satmat", new Day(db, "satmat"));
        dayHashMap.put("satnight", new Day(db, "satnight"));
    }

    public Day getCurrentDay() {
        return currentDay;
    }

    public void changeDayTo(String dayname) {
        currentDay = dayHashMap.get(dayname);
        currentDay.makeCurrentDay();
    }
}
