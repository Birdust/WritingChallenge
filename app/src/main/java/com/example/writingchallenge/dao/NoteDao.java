package com.example.writingchallenge.dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;


import com.example.writingchallenge.entities.Note;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM notes ORDER BY id DESC")
    List<Note> getAllNotes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void  insertNote(Note note);

    @Delete
    void DeleteNote(Note note);

    // Add a new method to insert a note into Firebase and your local Room database
    @Transaction
    default void insertNoteAndSyncWithFirebase(Note note, DatabaseReference firebaseDatabaseRef) {
        // Insert the new note into your local Room database
        insertNote(note);

        // Generate a unique key for the note in Firebase
        String noteKey = firebaseDatabaseRef.push().getKey();

        // Set the Firebase key in the Note object
        note.setFirebaseKey(noteKey);

        // Insert the note into Firebase Realtime Database using the Firebase key
        firebaseDatabaseRef.child(noteKey).setValue(note);
    }
}