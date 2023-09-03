package com.example.writingchallenge.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.writingchallenge.activities.Migration1to2;
import com.example.writingchallenge.dao.NoteDao;
import com.example.writingchallenge.entities.Note;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


@Database(entities = {Note.class}, version = 2, exportSchema = false)
public abstract class NotesDatabase extends RoomDatabase {
    private static NotesDatabase notesDatabase;
    private static DatabaseReference notesFirebaseRef;
    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // You can write SQL statements here to migrate your data
            // For example, if you added a new column "new_column" to your entity
            database.execSQL("ALTER TABLE notes ADD COLUMN new_column TEXT");
        }
    };

    public static synchronized NotesDatabase getDatabase(Context context){
        if(notesDatabase == null){
            notesDatabase = Room.databaseBuilder(
                            context, NotesDatabase.class,"notes_db")
                    .addMigrations(MIGRATION_1_2) // Add the migration
                    .build();
        }
        return notesDatabase;
    }

    public abstract NoteDao noteDao();

    // Firebase Realtime Database reference

    public static void initializeNotesFirebaseRef(DatabaseReference ref) {
        notesFirebaseRef = ref;
    }
    public static DatabaseReference getNotesFirebaseRef() {
        if (notesFirebaseRef == null) {
            throw new IllegalStateException("Firebase reference not initialized. Call initializeNotesFirebaseRef() first.");
        }
        return notesFirebaseRef;
    }
    // Method to delete a note and synchronize with Firebase
    public void deleteNoteAndSyncWithFirebase(Note noteToDelete) {
        // First, delete the note from your local Room database
        noteDao().DeleteNote(noteToDelete);

        // Next, delete the note from Firebase Realtime Database using its unique key
        DatabaseReference firebaseDatabaseRef = getNotesFirebaseRef();
        firebaseDatabaseRef.child(noteToDelete.getFirebaseKey()).removeValue();
    }
}
