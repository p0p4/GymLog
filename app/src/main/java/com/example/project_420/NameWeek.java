package com.example.project_420;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 *
 * @author Tino Behnen
 * @version 1.2
 */

public class NameWeek extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_week);
    }

    public void planWeekButton (View view)
    {
        String name = ((EditText)findViewById(R.id.nameWeekInput)).getText().toString();

        SharedPreferences sharedPrefs = getSharedPreferences("Week Data", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString("name", name);
        editor.apply();

        if (!name.equals(""))
        {
            Intent planWeekIntent = new Intent(this, PlanWeek.class);
            startActivity(planWeekIntent);
        }
        else
            {
                Toast.makeText(this, "Fill in required* fields!", Toast.LENGTH_LONG).show();
            }
    }
}