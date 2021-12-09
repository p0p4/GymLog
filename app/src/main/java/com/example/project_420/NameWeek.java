package com.example.project_420;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 *
 * @author Tino Behnen
 * @version 1.0
 */

public class NameWeek extends AppCompatActivity
{
    public String weekPlanName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_week);
    }

    public void planWeekButton (View view)
    {
        weekPlanName = ((EditText)findViewById(R.id.nameWeekInput)).getText().toString();

        if (!weekPlanName.equals(""))
        {
            Intent planWeekIntent = new Intent(this, PlanWeek.class);
            startActivity(planWeekIntent);
        }
    }
}