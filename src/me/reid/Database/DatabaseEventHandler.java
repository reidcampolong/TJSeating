package me.reid.Database;

import com.google.cloud.firestore.EventListener;
import com.google.cloud.firestore.FirestoreException;
import com.google.cloud.firestore.QuerySnapshot;

import javax.annotation.Nullable;

/**
 * Single handler per event
 */
public class DatabaseEventHandler implements EventListener<QuerySnapshot> {

    public DatabaseEventHandler() {

    }

    @Override
    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirestoreException e) {

    }
}
