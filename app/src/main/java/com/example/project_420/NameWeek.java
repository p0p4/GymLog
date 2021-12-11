package com.example.project_420;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

/**
 * Activity class for naming the week plan
 * @author Tino Behnen
 * @version 1.3
 */
public class NameWeek extends AppCompatActivity
{
    private static final String TAG = "NameWeek";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_week);

        Log.d(TAG, "onCreate() — called");

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.app_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    public void planWeekButton (View view)
    {
        String name = ((EditText)findViewById(R.id.nameWeekInput)).getText().toString();

        if (!name.equals(""))
        {
            SharedPreferences sharedPrefs = getSharedPreferences("Week Data", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putString("name", name);
            editor.apply();

            Intent planWeekIntent = new Intent(this, PlanWeek.class);
            startActivity(planWeekIntent);
        }
        else
        {
            Toast.makeText(this, "Fill in required* fields!", Toast.LENGTH_LONG).show();
        }
    }
}