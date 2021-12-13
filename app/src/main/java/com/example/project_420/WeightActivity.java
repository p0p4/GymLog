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
import androidx.appcompat.widget.SwitchCompat;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author matiasnäppä
 * versio 1.2
 */
public class WeightActivity extends AppCompatActivity {

    private float weight, previousWeight;
    private int weightPoints, i;
    private static final String weightPref = "weightPref", weightPointsPref = "weightPointsPref";
    private ArrayList<Float> weightList;
    private SwitchCompat toggleLossGain;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.app_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        weightList = new ArrayList<>();

        Button saveWeight = findViewById(R.id.btn_SaveWeight);
        toggleLossGain = findViewById(R.id.toggleLossGain);

        TextView tvPreviousWeight = findViewById(R.id.tvPreviousWeight);
        TextView weightInput = findViewById(R.id.weightInput);

        SharedPreferences gymlogPrefs = getSharedPreferences("gymlogPrefs", Context.MODE_PRIVATE);
        previousWeight = gymlogPrefs.getFloat(weightPref, 0);
        weightPoints = gymlogPrefs.getInt(weightPointsPref, 0);

        SharedPreferences weightPrefs = getSharedPreferences("weightPrefs", Context.MODE_PRIVATE);
        int weightIndex = weightPrefs.getInt("size", 0);
        for (i = 0; i < weightIndex; i++) {
            weightList.add(weightPrefs.getFloat(Integer.toString(i), 0));
        }
        toggleLossGain.setChecked(weightPrefs.getBoolean("LossGain", false));

        tvPreviousWeight.setText("Your previous weight was: " + (previousWeight) + " kg.");

        weightInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = weightInput.getText().toString();
                saveWeight.setEnabled(!input.contains(" ") && !input.contains(",") && !input.contains("-") && input.indexOf(".") == input.lastIndexOf("."));
            }
        });
    }

    public void saveWeight(View view) {
        EditText weightInput = findViewById(R.id.weightInput);

        if (!weightInput.getText().toString().equals("")) {
            weight = Float.parseFloat(weightInput.getText().toString());

            if (previousWeight == 0) {
                weightPoints = 0;
                previousWeight = weight;
            } else if ((weight < previousWeight) && toggleLossGain.isChecked()) {
                weightPoints += (int) (previousWeight - weight);
                previousWeight = weight;
            } else if ((weight < previousWeight) && !toggleLossGain.isChecked()) {
                weightPoints -= (int) (previousWeight - weight);
                previousWeight = weight;
            } else if ((weight > previousWeight) && toggleLossGain.isChecked()) {
                weightPoints -= (int) (weight - previousWeight);
                previousWeight = weight;
            } else if ((weight > previousWeight) && !toggleLossGain.isChecked()) {
                weightPoints += (int) (weight - previousWeight);
                previousWeight = weight;
            }
            weightList.add(weight);

            SharedPreferences weightPrefs = getSharedPreferences("weightPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = weightPrefs.edit();
            for (i = 0; i < weightList.size(); i++) {
                editor.putFloat(Integer.toString(i), weightList.get(i));
            }
            editor.putInt("size", weightList.size());
            editor.putBoolean("LossGain", toggleLossGain.isChecked());
            editor.apply();

            SharedPreferences gymlogPrefs = getSharedPreferences("gymlogPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor gymlogPrefsEditor = gymlogPrefs.edit();
            gymlogPrefsEditor.putFloat(weightPref, previousWeight);
            gymlogPrefsEditor.putInt(weightPointsPref, weightPoints);
            gymlogPrefsEditor.apply();

            Intent main = new Intent(this, MainActivity.class);
            startActivity(main);
        }
    }

    /**
     * Ask user if they are sure they want to delete all data.
     * if yes: Deletes all weight data from sharedpreferences.
     * Refreshes the textviews.
     * if no: nothing happens.
     */
    @SuppressLint("SetTextI18n")
    public void deleteWeight(View view) {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to delete all weight data?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> {
                    SharedPreferences weightPrefs = getSharedPreferences("weightPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor weightPrefsEditor = weightPrefs.edit();
                    weightPrefsEditor.clear();
                    weightPrefsEditor.apply();

                    SharedPreferences gymlogPrefs = getSharedPreferences("gymlogPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor gymlogPrefsEditor = gymlogPrefs.edit();
                    gymlogPrefsEditor.putFloat(weightPref, 0);
                    gymlogPrefsEditor.putInt(weightPointsPref, 0);
                    gymlogPrefsEditor.apply();

                    TextView tvPreviousWeight = findViewById(R.id.tvPreviousWeight);
                    tvPreviousWeight.setText("Your previous weight was: " + (0.0) + " kg.");

                    previousWeight = 0;
                    weightPoints = 0;
                })
                .setNegativeButton("No", null)
                .show();
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}