package com.example.writingchallenge.database;

import com.example.writingchallenge.entities.Note;
import com.google.firebase.database.DatabaseReference;

public class FirebaseRepository {
    private final DatabaseReference firebaseDatabaseRef;

    public FirebaseRepository(DatabaseReference firebaseDatabaseRef) {
        this.firebaseDatabaseRef = firebaseDatabaseRef;
    }

    public void insertNoteIntoFirebase(Note note) {
        // Generate a unique key for the note in Firebase
        String noteKey = firebaseDatabaseRef.push().getKey();

        // Set the Firebase key in the Note object
        note.setFirebaseKey(noteKey);

        // Insert the note into Firebase Realtime Database using the Firebase key
        firebaseDatabaseRef.child(noteKey).setValue(note);
    }
}
