package com.example.project_420;

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

/**
 * @author matiasnäppä
 * versio 1.0
 */

public class SleepActivity extends AppCompatActivity {

    private float sleep, sleepTarget;
    private int sleepPoints,i, sleepIndex;


    private Button btnIncrementTarget;
    private Button btnDecreaseTarget;
    private Button btn_SaveWeight;
    private TextView tvSleepPoints;
    private TextView tvSleepTarget;
    private EditText weightInput;

    public static final String sleepPointsPref = "sleepPointsPref";
    public static final String sleepTargetPref = "sleepTargetPref";

    ArrayList<Float> sleepList = new ArrayList<>();

    private final View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnIncrementTarget:
                    incrementTarget();
                    break;
                case R.id.btnDecreaseTarget:
                    decreaseTarget();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.app_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        btnIncrementTarget = findViewById(R.id.btnIncrementTarget);
        btnIncrementTarget.setOnClickListener(clickListener);

        btnDecreaseTarget = findViewById(R.id.btnDecreaseTarget);
        btnDecreaseTarget.setOnClickListener(clickListener);

        TextView tvSleepTarget = findViewById(R.id.tvSleepTarget);
        TextView tvSleepPoints = findViewById(R.id.tvSleepPoints);

        SharedPreferences sleepPrefs = getSharedPreferences("sleepPrefs", Context.MODE_PRIVATE);
        sleepIndex = sleepPrefs.getInt("size",0);
        for(i = 0; i < sleepIndex; i++) {
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

    public void incrementTarget() {
        TextView tvSleepTarget = findViewById(R.id.tvSleepTarget);
        sleepTarget = getSleepTarget() + 1;
        tvSleepTarget.setText("Your sleep target is " + sleepTarget + " hours.");
    }

    public void decreaseTarget() {
        TextView tvSleepTarget = findViewById(R.id.tvSleepTarget);
        sleepTarget = getSleepTarget() - 1;
        tvSleepTarget.setText("Your sleep target is " + sleepTarget + " hours.");
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