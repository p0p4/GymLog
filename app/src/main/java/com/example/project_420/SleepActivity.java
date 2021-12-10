package com.example.project_420;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 *
 * @author matiasnäppä
 * versio 1.0
 */

public class SleepActivity extends AppCompatActivity {

    private float sleep, sleepTarget;
    private int sleepPoints;

    public int getSleepPoints() {
        return sleepPoints;
    }

    public void setSleepPoints(int sleepPoints) {
        this.sleepPoints = sleepPoints;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.app_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        TextView tvPreviousSleep = (TextView) findViewById(R.id.tvPreviousSleep);
        tvPreviousSleep.setText("Your sleep target is: " + (Float.toString(sleepTarget)) + " hours.");
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

    public void saveSleep(View view) {
        EditText sleepInput = (EditText) findViewById(R.id.sleepInput);
        sleep =  Integer.parseInt(sleepInput.getText().toString());
        if (sleep > sleepTarget) {
            sleepPoints += sleep - sleepTarget;
        } else {
            sleepPoints -= sleepTarget - sleep;
        }
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }
}