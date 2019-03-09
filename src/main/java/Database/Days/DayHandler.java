package main.java.Database.Days;

import com.google.cloud.firestore.Firestore;
import main.java.Utilities.Utilities;

import java.util.HashMap;

/**
 * Manages the different datasets of days
 */
public class DayHandler {

    private Firestore db;
    private Day currentDay;
    private HashMap<String, Day> dayHashMap;

    public DayHandler(Firestore db) {
        this.db = db;
        dayHashMap = new HashMap<>(4);
        createDay("thursday");
    }

    public Day getCurrentDay() {
        return currentDay;
    }

    private void createDay(String dayname) {
        dayHashMap.put(dayname, new Day(db, dayname));
    }

    public void changeDayTo(String dayname) {

        boolean alert = false;
        if (!dayHashMap.containsKey(dayname)) {
            createDay(dayname);
            alert = true;
        }

        currentDay = dayHashMap.get(dayname);
        currentDay.makeCurrentDay();

        if (alert)
            Utilities.createNicePopup("TJ Seating Data", "Loading data for " + dayname + " for the first time.\nBy the time you close this popup, it should be loaded!");
    }
}
