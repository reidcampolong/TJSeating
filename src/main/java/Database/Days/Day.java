package main.java.Database.Days;

import com.google.cloud.firestore.*;
import main.java.Client;
import main.java.Database.Database;
import main.java.Section.Seat.Seat;
import main.java.Section.Seat.SeatHandler;
import main.java.Section.Seat.Status;
import main.java.Section.Section;
import main.java.Section.SectionHandler;
import main.java.Utilities.Log;

import java.util.ArrayList;
import java.util.List;

public class Day {

    private Database database;
    private Firestore db;
    private String databaseName;

    private Section leftSection, middleSection, rightSection;

    public Day(Firestore db, String databaseName) {
        this.db = db;
        //this.database = database;
        this.databaseName = databaseName;
        leftSection = new Section(SectionHandler.sectionAValues[0], SectionHandler.sectionAValues[1], SectionHandler.sectionAValues[2]);
        middleSection = new Section(SectionHandler.sectionBValues[0], SectionHandler.sectionBValues[1], SectionHandler.sectionBValues[2]);
        rightSection = new Section(SectionHandler.sectionCValues[0], SectionHandler.sectionCValues[1], SectionHandler.sectionCValues[2]);
        initializeListeners();
    }

    public void makeCurrentDay() {
        Client.getSectionHandler().updateSectionsToGrid(leftSection, middleSection, rightSection);
    }

    public void initializeListeners() {
        initListener(leftSection);
        initListener(middleSection);
        initListener(rightSection);
    }

    //public void updateSeatMap(Seat[][] seatMap, )

    private void initListener(Section section) {
        String sectionID = String.valueOf(section.getSectionNumber());
        CollectionReference collectionReference = db.collection("days").document(databaseName).collection(sectionID);
        collectionReference.addSnapshotListener((snapshots, e) -> {
            if (snapshots != null) {
                for (DocumentChange change : snapshots.getDocumentChanges()) {
                    Log.i("Received update " + change.getDocument().getId() + " " + change.getDocument().toString());
                    updateSeatByDoc(section, change.getDocument());
                }
            }
        });
    }

    private void updateSeatByDoc(Section section, QueryDocumentSnapshot document) {
        Log.i("Updating seat to " + document.getData().get("status").toString());
        Seat seat = section.getSeatByName(document.getId());
        Status status = Status.valueOf(document.getData().get("status").toString());
        String holder = document.getData().get("holder").toString();
        SeatHandler.updateSeat(seat, status, holder);
    }

    public String getDatabaseName() {
        return databaseName;
    }
}
