package com.example.project_420;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.app_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    public void startButton (View view) {
        Intent intent = new Intent(this, StartWorkoutActivity.class);
        startActivity(intent);
    }

    public void progressButton (View view) {
        Intent progressIntent = new Intent(this, ProgressionGraphActivity.class);
        startActivity(progressIntent);
    }

    public void nameWeekButton (View view) {
        Intent nameWeekIntent = new Intent(this, NameWeek.class);
        startActivity(nameWeekIntent);
    }
}