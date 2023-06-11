package com.example.writingchallenge.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.writingchallenge.R;
import com.example.writingchallenge.adapter.NotesAdapter;
import com.example.writingchallenge.database.NotesDatabase;
import com.example.writingchallenge.entities.Note;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity   {

    public static final int REQUEST_CODE_ADD_NOTE = 1;

    private RecyclerView notesRecycleView;
    private List<Note> noteList;
    private NotesAdapter notesAdapter;
    TextView userProfile;

    TextView intentChallenge;
    static TextView challengeText;

    int challenge1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageNote = findViewById(R.id.arrow);
        ImageView imageBook = findViewById(R.id.book1);
        challengeText = findViewById(R.id.challenge1);
        intentChallenge = findViewById(R.id.challenge2);

        imageNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(
                        new Intent(getApplicationContext(), CreateNoteActivity.class),
                        REQUEST_CODE_ADD_NOTE);}
        });

        intentChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(
                        new Intent(getApplicationContext(), challengeActivities.class),
                        REQUEST_CODE_ADD_NOTE);}
        });

        imageBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getApplicationContext(), stickerActivities.class), REQUEST_CODE_ADD_NOTE);
            }
        });



        userProfile = findViewById(R.id.name);
        ShowName();

        notesRecycleView = findViewById(R.id.notesRecyclerView);
        notesRecycleView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        noteList = new ArrayList<>();
        notesAdapter = new NotesAdapter(noteList);
        notesRecycleView.setAdapter(notesAdapter);

        getNotes();


    }


    private void getNotes (){

        @SuppressLint("StaticFieldLeak")
        class GetNotesTask extends AsyncTask<Void, Void, List<Note>> {

            @Override
            protected List<Note> doInBackground(Void... voids) {
                return NotesDatabase.getDatabase(getApplicationContext()).noteDao().getAllNotes();
            }

            @Override
            protected void onPostExecute(List<Note> notes) {
                super.onPostExecute(notes);
                if(noteList.size()==0){
                    noteList.addAll(notes);
                    notesAdapter.notifyDataSetChanged();

                }else {
                    noteList.add(0,notes.get(0));
                    notesAdapter.notifyItemInserted(0);
                }notesRecycleView.smoothScrollToPosition(0);
            }
        }
        new GetNotesTask().execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_NOTE && resultCode == RESULT_OK ){
            getNotes();
        }
    }

    public void ChallengeTask(){
        challengeText = findViewById(R.id.challenge1);
        if (noteList.size()!=0){
            int size = noteList.size();
            challengeText.setText("You have Completed"+ size +  "Challenge");
        }
    }

    public void ShowName(){
        Intent intent = getIntent();
        String nameUser = getIntent().getStringExtra("name");
        userProfile.setText(nameUser);
    }
}