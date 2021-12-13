package com.example.project_420;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
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
    private ArrayList<Float> sleepList;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.app_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        sleepList = new ArrayList<>();

        @SuppressLint("NonConstantResourceId")
        View.OnClickListener clickListener = view -> {
            switch (view.getId()) {
                case R.id.btnIncrementTarget:
                    incrementTarget();
                    break;
                case R.id.btnDecreaseTarget:
                    decreaseTarget();
                    break;
            }
        };

        Button btnIncrementTarget = findViewById(R.id.btnIncrementTarget);
        btnIncrementTarget.setOnClickListener(clickListener);
        Button btnDecreaseTarget = findViewById(R.id.btnDecreaseTarget);
        btnDecreaseTarget.setOnClickListener(clickListener);
        Button saveSleep = findViewById(R.id.btn_SaveSleep);

        TextView tvSleepTarget = findViewById(R.id.tvSleepTarget);
        TextView tvSleepPoints = findViewById(R.id.tvSleepPoints);
        TextView sleepInput = findViewById(R.id.sleepInput);

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

        sleepInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = sleepInput.getText().toString();
                saveSleep.setEnabled(!input.contains(" ") && !input.contains(",") && !input.contains("-") && input.indexOf(".") == input.lastIndexOf("."));
            }
        });
    }

    public void saveSleep(View view) {
        EditText sleepInput = findViewById(R.id.sleepInput);

        if (!sleepInput.getText().toString().equals("")) {
            sleepTarget = getSleepTarget();
            sleep = Float.parseFloat(sleepInput.getText().toString());

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
     * Ask user if they are sure they want to delete all data.
     * if yes: Deletes all sleep data from sharedpreferences.
     * Refreshes the textviews.
     * if no: nothing happens.
     */
    @SuppressLint("SetTextI18n")
    public void deleteSleep(View view) {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to delete all sleep data?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> {
                    SharedPreferences sleepPrefs = getSharedPreferences("sleepPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor sleepPrefsEditor = sleepPrefs.edit();
                    sleepPrefsEditor.clear();
                    sleepPrefsEditor.apply();

                    SharedPreferences gymlogPrefs = getSharedPreferences("gymlogPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor gymlogPrefsEditor = gymlogPrefs.edit();
                    gymlogPrefsEditor.putFloat(sleepTargetPref, 8);
                    gymlogPrefsEditor.putInt(sleepPointsPref, 0);
                    gymlogPrefsEditor.apply();

                    sleepTarget = 8;
                    sleepPoints = 0;

                    TextView tvSleepTarget = findViewById(R.id.tvSleepTarget);
                    TextView tvSleepPoints = findViewById(R.id.tvSleepPoints);

                    tvSleepTarget.setText("Your sleep target is " + 8.0 + " hours.");
                    tvSleepPoints.setText("Sleep balance: " + sleepPoints);
                })
                .setNegativeButton("No", null)
                .show();
    }

    public float getSleepTarget() {
        return sleepTarget;
    }
}