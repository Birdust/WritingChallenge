package com.example.writingchallenge.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.writingchallenge.R;

public class stickerActivities extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticker_activities);
        ImageView imageBack = findViewById(R.id.imageBack);
        ImageView sticker1 = findViewById(R.id.sticker1);

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {onBackPressed();}});

        if (CreateNoteActivity.challenge == 2){
            sticker1.setImageResource(R.drawable.real1);
        }

    }

}