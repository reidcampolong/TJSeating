package me.reid.Database;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.*;
import com.google.firebase.database.annotations.Nullable;
import com.google.firestore.v1beta1.Document;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Reid on 2/6/19.
 */
public class Database {

    private FirebaseDatabase database;
    private DatabaseReference ref;

    public Database() {
        initializeConnection();
    }

    private void initializeConnection() {

        InputStream serviceAccount = null;
        try {
            serviceAccount = new FileInputStream("res/tjseating-firebase-adminsdk-rwm1i-e7baee71c0.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        GoogleCredentials credentials = null;
        try {
            credentials = GoogleCredentials.fromStream(serviceAccount);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //FirebaseOptions options = new FirebaseOptions.Builder()
        //      .setCredentials(credentials)
        //    .build();
        //FirebaseApp.initializeApp(options);

        FirestoreOptions options = FirestoreOptions.newBuilder().setCredentials(credentials).setTimestampsInSnapshotsEnabled(true).build();
        Firestore db = options.getService();
        CollectionReference collectionReference = db.collection("thursday");
        collectionReference.addSnapshotListener((snapshot, e) -> {

            if (e != null) {
                System.err.println("Listen failed: " + e);
                return;
            }

            if (snapshot != null) {
                System.out.println("Current data: " + snapshot.getDocuments());
                for(QueryDocumentSnapshot d : snapshot.getDocuments()) {
                    System.out.println(d.getData());
                }
            } else {
                System.out.print("Current data: null");
            }
        });

    }
}
