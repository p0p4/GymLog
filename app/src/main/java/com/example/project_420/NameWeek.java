package com.example.project_420;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 *
 * @author Tino Behnen
 * @version 1.1
 */

public class NameWeek extends AppCompatActivity
{
    private Week week;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_week);

        week = Week.getInstance();
    }

    public void planWeekButton (View view)
    {
        week.setWeekName(((EditText)findViewById(R.id.nameWeekInput)).getText().toString());

        if (!week.getWeekName().equals(""))
        {
            Intent planWeekIntent = new Intent(this, PlanWeek.class);
            startActivity(planWeekIntent);
        }
    }
}