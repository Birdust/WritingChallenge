package com.example.writingchallenge.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writingchallenge.R;
import com.example.writingchallenge.entities.Note;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewMemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_memo);
        String noteId = getIntent().getStringExtra("noteId");
        String noteTitle = getIntent().getStringExtra("noteTitle");

        // Assuming you have TextViews with IDs "textTitle" and "textContent" in your layout
        TextView titleTextView = findViewById(R.id.textTitle);
        TextView contentTextView = findViewById(R.id.textContent);

        if (noteId != null && !noteId.isEmpty()) {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("notes");

            databaseReference.child(noteId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        Note note = dataSnapshot.getValue(Note.class);
                        if (note != null) {

                            String title = note.getTitle();
                            String content = note.getNoteText();

                            titleTextView.setText(title);
                            contentTextView.setText(content);
                        }
                    } else {
                        // Handle the case where the note with the specified ID doesn't exist
                        // You can display an error message or handle it as needed
                    }
                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle Firebase database errors here
                    Log.e("ViewMemoActivity", "Firebase Error: " + databaseError.getMessage());
                    // You can display an error message or handle it as needed
                }
            });
        } else {
            // Handle the case where noteId is null or empty, e.g., display an error message or return to the previous activity
        }
    }
}
