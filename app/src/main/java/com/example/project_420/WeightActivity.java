package com.example.project_420;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 *
 * @author matiasnäppä
 * versio 1.0
 */

public class WeightActivity extends AppCompatActivity {

    private float weight, previousWeight;
    private int weightPoints;
    private boolean weightLoss;
    int i,weightindex;

    public static final String weightPref = "weightPref";
    public static final String weightPointsPref = "weightPointsPref";

    ArrayList<Float> weightlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.app_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        SharedPreferences gymlogPrefs = getSharedPreferences("gymlogPrefs", Context.MODE_PRIVATE);
        previousWeight = gymlogPrefs.getFloat(weightPref, 0);
        weightPoints = gymlogPrefs.getInt(weightPointsPref, 0);

        SharedPreferences weightPrefs = getSharedPreferences("weightPrefs", Context.MODE_PRIVATE);
        weightindex = weightPrefs.getInt("size",0);
        for(i = 0;i < weightindex;i++) {
            weightlist.add(weightPrefs.getFloat(Integer.toString(i), 0));
        }

        TextView tvPreviousWeight = (TextView) findViewById(R.id.tvPreviousWeight);
        tvPreviousWeight.setText("Your previous weight was: " + (Float.toString(previousWeight)) + " kg.");

        Switch toggleLossGain = findViewById(R.id.toggleLossGain);

        toggleLossGain.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    weightLoss = true;
                } else {
                    weightLoss = false;
                }
            }
        });
    }

    public void saveWeight(View view) {
        EditText weightInput = (EditText) findViewById(R.id.weightInput);
        weight =  Float.parseFloat(weightInput.getText().toString());

        //jos paino on vähemmän kuin viimeksi, pisteet erotuksesta jne.
        if ((weight < previousWeight) && (weightLoss = true)) { //painan vähemmän kuin ennen ja haluan pudottaa painoa
            weightPoints += (int) (previousWeight - weight);
            previousWeight = weight;
        } else if ((weight < previousWeight) && (weightLoss = false)) { // painan vähemmän kuin ennen mutta en halua pudottaa painoa
            weightPoints -= (int) (previousWeight - weight);
            previousWeight = weight;
        } else if ((weight > previousWeight) && (weightLoss = true)) { // painan enemmän kuin ennen mutta haluan pudottaa painoa
            weightPoints -= (int) (weight - previousWeight);
            previousWeight = weight;
        } else if ((weight > previousWeight) && (weightLoss = false)) { // painan enemmän kuin ennen mutta en halua pudottaa painoa
            weightPoints += (int) (weight - previousWeight);
            previousWeight = weight;
        }

        weightlist.add(weight);
        SharedPreferences weightPrefs = getSharedPreferences("weightPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = weightPrefs.edit();
        for (i = 0;i < weightlist.size();i++) {
            editor.putFloat(Integer.toString(i), weightlist.get(i));
        }
        editor.putInt("size",weightlist.size());
        editor.apply();

        SharedPreferences gymlogPrefs = getSharedPreferences("gymlogPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor gymlogPrefsEditor = gymlogPrefs.edit();
        gymlogPrefsEditor.putFloat(weightPref, previousWeight);
        gymlogPrefsEditor.putInt(weightPointsPref, weightPoints);
        gymlogPrefsEditor.apply();

        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }

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
}