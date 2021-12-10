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

public class WeightActivity extends AppCompatActivity {

    private float weight, previousWeight;
    private int weightPoints;

    public float getPreviousWeight() {
        return previousWeight;
    }

    public void setPreviousWeight(float previousWeight) {
        this.previousWeight = previousWeight;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getWeightPoints() {
        return weightPoints;
    }

    public void setWeightPoints(int weightPoints) {
        this.weightPoints = weightPoints;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.app_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        TextView tvPreviousWeight = (TextView) findViewById(R.id.tvPreviousWeight);
        tvPreviousWeight.setText("Your previous weight was: " + (Float.toString(previousWeight)) + " kg.");
    }

    public void saveWeight(View view) {
        EditText weightInput = (EditText) findViewById(R.id.weightInput);
        weight =  Integer.parseInt(weightInput.getText().toString());
        if (weight < previousWeight) {
            weightPoints += previousWeight - weight;
            previousWeight = weight;
        } else {
            weightPoints -= weight - previousWeight;
            previousWeight = weight;
        }
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }
}