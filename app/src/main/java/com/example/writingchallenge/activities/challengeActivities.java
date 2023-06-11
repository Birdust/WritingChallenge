package com.example.writingchallenge.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.writingchallenge.R;

public class challengeActivities extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_activities);
        ImageView imageBack = findViewById(R.id.imageBack);
        TextView first = findViewById(R.id.Challenge1);

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {onBackPressed();}});

        if (CreateNoteActivity.challenge == 2){
            first.setText("You completed the challenge");
        }
    }
}