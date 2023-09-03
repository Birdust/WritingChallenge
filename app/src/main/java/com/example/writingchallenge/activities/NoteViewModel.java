package com.example.writingchallenge.activities;

import androidx.lifecycle.ViewModel;

import com.example.writingchallenge.dao.NoteDao;
import com.example.writingchallenge.database.FirebaseRepository;
import com.example.writingchallenge.entities.Note;
import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NoteViewModel extends ViewModel {
    private final NoteDao noteDao;
    private final FirebaseRepository firebaseRepository;

    public NoteViewModel(NoteDao noteDao, DatabaseReference firebaseDatabaseRef) {
        this.noteDao = noteDao;
        this.firebaseRepository = new FirebaseRepository(firebaseDatabaseRef);
    }
    private String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
    public void createNewNoteAndSyncWithFirebase() {
        Note newNote = new Note();
        newNote.setTitle("New Note Title");
        newNote.setDateTime(getCurrentDateTime());
        // Set other properties as needed

        // Insert the new note into the local Room database
        noteDao.insertNote(newNote);

        // Insert the new note into Firebase and sync it
        firebaseRepository.insertNoteIntoFirebase(newNote);
    }
}
