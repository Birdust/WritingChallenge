package com.example.writingchallenge.dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.writingchallenge.entities.Note;

import java.util.List;

@Dao
public interface NoteDao { // 여기부터 SQL 다시

    @Query("SELECT * FROM notes ORDER BY id DESC")
    List<Note> getAllNotes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void  insertNote(Note note);

    @Delete
    void DeleteNote(Note note);
}