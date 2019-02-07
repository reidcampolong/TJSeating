package main.java.Database;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.database.*;
import main.java.Client;
import main.java.Section.Seat.Seat;
import main.java.Section.Seat.SeatHandler;
import main.java.Section.Seat.Status;
import main.java.Section.Section;
import main.java.Utilities.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by Reid on 2/6/19.
 */
public class Database {

    public static String currentDay = "thursday";
    private String collectionDatapath = "days.{day}.{section}";

    public enum Table {
        THURSDAY, FRIDAY, SATMAT, SATNIGHT;
    }

    private Client client;
    private FirebaseDatabase database;
    private Firestore db;
    private DatabaseReference ref;

    private List<ListenerRegistration> currentListeners;

    public Database(Client client) {
        this.client = client;
        this.currentListeners = new ArrayList<>();
        initializeConnection();
    }

    private void initializeConnection() {
        Log.i("Creating database connection.");
        GoogleCredentials credentials = null;
        InputStream serviceAccount;
        try {
            System.out.println(getClass().getResourceAsStream("main/resources/account.json"));
            serviceAccount = getClass().getResourceAsStream("/account.json");
            credentials = GoogleCredentials.fromStream(serviceAccount);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FirestoreOptions options = FirestoreOptions.newBuilder().setCredentials(credentials).setTimestampsInSnapshotsEnabled(true).build();
        db = options.getService();

        //createCollectionListener(db, "days", "thursday", "0");
        System.out.println("Try to grab data:");
        try {
            switchViewTo(currentDay);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchViewTo(String dayName) {
        currentDay = dayName;
        registerDayListener(db, currentDay);
    }

    /**
     * Update a seat visually from the database
     *
     * @param section
     * @param document
     */
    private void updateSeatByDoc(Section section, QueryDocumentSnapshot document) {
        Seat seat = section.getSeatByName(document.getId());
        Status status = Status.valueOf(document.getData().get("status").toString());
        String holder = document.getData().get("holder").toString();
        SeatHandler.updateSeat(seat, status, holder);
    }

    private void updateSeatByDoc(Section section, DocumentChange dc) {
        updateSeatByDoc(section, dc.getDocument());
    }

    public void writeSeatToDoc(String dayname, Seat seat) {
        Map<String, Object> docData = new HashMap<>();
        docData.put("holder", seat.getSeatHolder());
        docData.put("status", seat.getSeatStatus().toString());

        db.collection("days").document(currentDay)
                .collection(String.valueOf(seat.getSectionNumber())).document(seat.getSectionTitle()).set(docData);
        System.out.println("Wrote seat to document.");
    }

    public void writeSeatToDoc(Seat seat) {
        writeSeatToDoc(currentDay, seat);
    }

    /**
     * Save all seats in a section to the dayname
     *
     * @param dayname
     * @param section
     */
    private void saveSectionToDoc(String dayname, Section section) {
        Seat[][] seats = section.getAllSeats();
        for (int col = 0; col < seats.length; col++) {
            for (int row = 0; row < seats[col].length; row++) {
                writeSeatToDoc(dayname, seats[col][row]);
            }
        }
    }

    /**
     * Save everything currently on screen to a database under the dayName
     *
     * @param dayName
     */
    public void saveDayToDoc(String dayName) {
        Section sec1 = Client.getSectionHandler().getSection(0);
        Section sec2 = Client.getSectionHandler().getSection(1);
        Section sec3 = Client.getSectionHandler().getSection(2);
        saveSectionToDoc(dayName, sec1);
        saveSectionToDoc(dayName, sec2);
        saveSectionToDoc(dayName, sec3);
    }

    /**
     * Starts a listener for database events
     *
     * @param db
     */
    private void registerDayListener(Firestore db, String dayName) {
        if (currentListeners.size() > 0)
            removeOldListeners();
        createListenerFor(dayName, Client.getSectionHandler().getSection(0));
        createListenerFor(dayName, Client.getSectionHandler().getSection(1));
        createListenerFor(dayName, Client.getSectionHandler().getSection(2));
        Log.i("Data listener switch to " + dayName);
    }

    private void removeOldListeners() {
        Iterator it = currentListeners.iterator();
        while (it.hasNext()) {
            ListenerRegistration r = (ListenerRegistration) it.next();
            r.remove();
            it.remove();
        }
    }

    private void createListenerFor(String dayName, final Section section) {
        CollectionReference collectionReference = db.collection("days").document(dayName).collection(String.valueOf(section.getSectionNumber()));
        ListenerRegistration listener = collectionReference.addSnapshotListener((snapshot, e) -> {
            if (snapshot != null) {
                for (DocumentChange d : snapshot.getDocumentChanges())
                    updateSeatByDoc(section, d);
            }
        });
        currentListeners.add(listener);
    }
}
