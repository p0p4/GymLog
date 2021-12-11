package com.example.project_420;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int fitnessIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.app_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        updateViews();
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

    public void sleepButton (View view) {
        Intent sleepIntent = new Intent(this, SleepActivity.class);
        startActivity(sleepIntent);
    }

    public void weightButton (View view) {
        Intent weightIntent = new Intent(this, WeightActivity.class);
        startActivity(weightIntent);
    }

    public void updateViews() {
        TextView tvProgramString = findViewById(R.id.tvProgramString);
        String programStringDefault = "You don't currently have a workout plan";
        tvProgramString.setText(programStringDefault);

        TextView tvFitnessIndex = findViewById(R.id.tvFitnessIndex);

        SharedPreferences gymlogPrefs = getSharedPreferences("gymlogPrefs", Context.MODE_PRIVATE);
        fitnessIndex += ((gymlogPrefs.getInt("weightPointsPref", 0)) + ((gymlogPrefs.getInt("sleepPointsPref", 0))));
        tvFitnessIndex.setText("Performance under current plan: " + fitnessIndex);
    }

    public int getFitnessIndex() {
        return fitnessIndex;
    }

    public void setFitnessIndex(int newFitnessIndex) {
        this.fitnessIndex = newFitnessIndex;
    }
}