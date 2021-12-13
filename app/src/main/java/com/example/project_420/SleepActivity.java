package com.example.project_420;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author matiasnäppä
 * versio 1.2
 */

public class SleepActivity extends AppCompatActivity {

    private float sleep, sleepTarget;
    private int sleepPoints, i;
    private static final String sleepPointsPref = "sleepPointsPref", sleepTargetPref = "sleepTargetPref";
    ArrayList<Float> sleepList;

    @SuppressLint("NonConstantResourceId")
    private final View.OnClickListener clickListener = view -> {
        switch (view.getId()) {
            case R.id.btnIncrementTarget:
                incrementTarget();
                break;
            case R.id.btnDecreaseTarget:
                decreaseTarget();
                break;
        }
    };

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.app_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        sleepList = new ArrayList<>();

        Button btnIncrementTarget = findViewById(R.id.btnIncrementTarget);
        btnIncrementTarget.setOnClickListener(clickListener);

        Button btnDecreaseTarget = findViewById(R.id.btnDecreaseTarget);
        btnDecreaseTarget.setOnClickListener(clickListener);

        TextView tvSleepTarget = findViewById(R.id.tvSleepTarget);
        TextView tvSleepPoints = findViewById(R.id.tvSleepPoints);

        SharedPreferences sleepPrefs = getSharedPreferences("sleepPrefs", Context.MODE_PRIVATE);
        int sleepIndex = sleepPrefs.getInt("size", 0);
        for (i = 0; i < sleepIndex; i++) {
            sleepList.add(sleepPrefs.getFloat(Integer.toString(i), 0));
        }

        SharedPreferences gymlogPrefs = getSharedPreferences("gymlogPrefs", Context.MODE_PRIVATE);
        sleepTarget = gymlogPrefs.getFloat(sleepTargetPref, 8);
        sleepPoints = gymlogPrefs.getInt(sleepPointsPref, 0);

        tvSleepTarget.setText("Your sleep target is " + sleepTarget + " hours.");
        tvSleepPoints.setText("Sleep balance: " + (sleepPoints));
    }

    public void saveSleep(View view) {
        EditText sleepInput = findViewById(R.id.sleepInput);

        if (!sleepInput.getText().toString().equals("")) {
            sleepTarget = getSleepTarget();
            sleep = Integer.parseInt(sleepInput.getText().toString());

            if (sleep > sleepTarget) {
                sleepPoints += sleep - sleepTarget;
            } else {
                sleepPoints -= sleepTarget - sleep;
            }

            sleepList.add(sleep);
            SharedPreferences sleepPrefs = getSharedPreferences("sleepPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sleepPrefs.edit();
            for (i = 0; i < sleepList.size(); i++) {
                editor.putFloat(Integer.toString(i), sleepList.get(i));
            }
            editor.putInt("size", sleepList.size());
            editor.apply();

            SharedPreferences gymlogPrefs = getSharedPreferences("gymlogPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor gymlogPrefsEditor = gymlogPrefs.edit();

            gymlogPrefsEditor.putFloat(sleepTargetPref, sleepTarget);
            gymlogPrefsEditor.putInt(sleepPointsPref, sleepPoints);

            gymlogPrefsEditor.apply();

            Intent main = new Intent(this, MainActivity.class);
            startActivity(main);
        }
    }

    @SuppressLint("SetTextI18n")
    public void incrementTarget() {
        sleepTarget = getSleepTarget() + 1;
        ((TextView) findViewById(R.id.tvSleepTarget))
                .setText("Your sleep target is " + sleepTarget + " hours.");
    }

    @SuppressLint("SetTextI18n")
    public void decreaseTarget() {
        sleepTarget = getSleepTarget() - 1;
        ((TextView) findViewById(R.id.tvSleepTarget))
                .setText("Your sleep target is " + sleepTarget + " hours.");
    }

    /**
     * Deletes all sleep data from sharedpreferences.
     * Refreshes the textviews.
     */

    public void deleteSleep(View view) {
        SharedPreferences sleepPrefs = getSharedPreferences("sleepPrefs", MODE_PRIVATE);
        SharedPreferences.Editor sleepPrefsEditor = sleepPrefs.edit();
        sleepPrefsEditor.clear();
        sleepPrefsEditor.apply();

        SharedPreferences gymlogPrefs = getSharedPreferences("gymlogPrefs",MODE_PRIVATE);
        SharedPreferences.Editor gymlogPrefsEditor = gymlogPrefs.edit();
        gymlogPrefsEditor.putFloat(sleepTargetPref,0);
        gymlogPrefsEditor.putInt(sleepPointsPref,0);
        gymlogPrefsEditor.apply();

        TextView tvSleepTarget = findViewById(R.id.tvSleepTarget);
        TextView tvSleepPoints = findViewById(R.id.tvSleepPoints);

        tvSleepTarget.setText("Your sleep target is " + 8 + " hours.");
        tvSleepPoints.setText("Sleep balance: " + 0);
    }

    public int getSleepPoints() {
        return sleepPoints;
    }

    public void setSleepPoints(int sleepPoints) {
        this.sleepPoints = sleepPoints;
    }

    public float getSleep() {
        return sleep;
    }

    public void setSleep(float sleep) {
        this.sleep = sleep;
    }

    public float getSleepTarget() {
        return sleepTarget;
    }

    public void setSleepTarget(float sleepTarget) {
        this.sleepTarget = sleepTarget;
    }
}