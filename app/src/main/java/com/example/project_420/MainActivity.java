package com.example.project_420;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private int fitnessIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.app_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        updateViews();
    }

    public void startButton(View view) {
        Intent intent = new Intent(this, StartWorkoutActivity.class);
        startActivity(intent);
    }

    public void progressButton(View view) {
        Intent progressIntent = new Intent(this, ProgressionGraphActivity.class);
        startActivity(progressIntent);
    }

    public void nameWeekButton(View view) {
        Intent nameWeekIntent = new Intent(this, NameWeek.class);
        startActivity(nameWeekIntent);
    }

    public void sleepButton(View view) {
        Intent sleepIntent = new Intent(this, SleepActivity.class);
        startActivity(sleepIntent);
    }

    public void weightButton(View view) {
        Intent weightIntent = new Intent(this, WeightActivity.class);
        startActivity(weightIntent);
    }

    @SuppressLint("SetTextI18n")
    public void updateViews() {
        String programStringDefault = "You don't currently have a workout plan.";
        SharedPreferences sharedPrefs = getSharedPreferences("Week Data", MODE_PRIVATE);
        String programCurrent = sharedPrefs.getString("name", "");

        TextView tvProgramString = findViewById(R.id.tvProgramString);

        if (programCurrent.equals("")) {
            tvProgramString.setText(programStringDefault);
        } else {
            tvProgramString.setText(programCurrent);
        }

        TextView tvFitnessIndex = findViewById(R.id.tvFitnessIndex);

        SharedPreferences gymlogPrefs = getSharedPreferences("gymlogPrefs", MODE_PRIVATE);
        fitnessIndex += ((gymlogPrefs.getInt("weightPointsPref", 0)) + ((gymlogPrefs.getInt("sleepPointsPref", 0))) + ((gymlogPrefs.getInt("workoutScore", 0))));
        tvFitnessIndex.setText("Performance under current plan: " + fitnessIndex);
    }

    /**
     * Ask user if they are sure they want to delete all data.
     * if yes: Deletes all workout data from sharedpreferences.
     * Refreshes the textviews.
     * if no: nothing happens.
     */

    @SuppressLint("SetTextI18n")
    public void clearProgram(View view) {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to delete all data?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> {
                    SharedPreferences gymlogPrefs = getSharedPreferences("gymlogPrefs", MODE_PRIVATE);
                    SharedPreferences sharedPrefs = getSharedPreferences("Week Data", MODE_PRIVATE);
                    SharedPreferences workoutPrefs = getSharedPreferences("workoutPrefs", MODE_PRIVATE);

                    SharedPreferences.Editor gymlogPrefsEditor = gymlogPrefs.edit();
                    SharedPreferences.Editor sharedPrefsEditor = sharedPrefs.edit();
                    SharedPreferences.Editor workoutPrefsEditor = workoutPrefs.edit();

                    gymlogPrefsEditor.clear();
                    sharedPrefsEditor.clear();
                    workoutPrefsEditor.clear();

                    gymlogPrefsEditor.apply();
                    sharedPrefsEditor.apply();
                    workoutPrefsEditor.apply();

                    TextView tvFitnessIndex = findViewById(R.id.tvFitnessIndex);

                    updateViews();
                    tvFitnessIndex.setText("Performance under current plan: " + 0);
                })
                .setNegativeButton("No", null)
                .show();
    }
}