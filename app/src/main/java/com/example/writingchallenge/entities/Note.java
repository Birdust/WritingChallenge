package com.example.writingchallenge.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.Transaction;

import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "notes")
public class Note implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    //private String content;
    private String firebaseKey; // Firebase unique key

    public String getFirebaseKey() {
        return firebaseKey;
    }

    public void setFirebaseKey(String firebaseKey) {
        this.firebaseKey = firebaseKey;
    }

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "date_time")
    private String dateTime;

    @ColumnInfo(name="subtitle")
    private String subtitle;

    @ColumnInfo(name = "note_text")
    private String noteText;

    @ColumnInfo (name = "image_path")
    private  String imagePath;

    @ColumnInfo(name = "color")
    private String color;

    @ColumnInfo(name = "web_link")
    private String webLink;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    @NonNull
    @Override
    public String toString() {
        return title + " : " + dateTime;
    }

    @Dao
    public interface NoteDao {
        @Query("SELECT * FROM notes ORDER BY id DESC")
        List<Note> getAllNotes();

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertNote(Note note);

        @Delete
        void DeleteNote(Note note);

        // Custom method to insert a note and sync with Firebase
        @Transaction
        void insertNoteAndSyncWithFirebase(Note note, DatabaseReference firebaseDatabaseRef);
    }
}
