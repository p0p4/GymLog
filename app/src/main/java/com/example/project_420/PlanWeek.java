package com.example.project_420;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 *
 * @author Tino Behnen
 * @version 1.0
 */

public class PlanWeek extends AppCompatActivity
{
    Week week;
    Day day = Day.MON;
    String name;
    int sets, reps, weight, duration, movementCount = 1;
    EditText nameEdit, setsEdit, repsEdit, weightEdit, durationEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_week);

        week = Week.getInstance();

        nameEdit = findViewById(R.id.nameInput);
        setsEdit = findViewById(R.id.setsInput);
        repsEdit = findViewById(R.id.repsInput);
        weightEdit = findViewById(R.id.weightInput);
        durationEdit = findViewById(R.id.durationInput);

        refreshTitle();
    }

    public void endWeek(View view)
    {
        newMovement();
        backToMain();
    }

    public void endDay(View view)
    {
        newMovement();
        movementCount = 1;

        if (day == Day.SUN)
        {
            backToMain();
        }
        else
        {
            day = Day.values()[day.ordinal() + 1];
            refreshTitle();
        }
    }

    public void nextMove(View view)
    {
        newMovement();
    }

    private void newMovement()
    {
        if (isEmpty(nameEdit) || isEmpty(setsEdit) || isEmpty(repsEdit))
        {
            return;
        }

        name = nameEdit.getText().toString();
        sets = Integer.parseInt(setsEdit.getText().toString());
        reps = Integer.parseInt(repsEdit.getText().toString());

        if (isEmpty(weightEdit))
            weight = 0;
        else
            weight = Integer.parseInt(weightEdit.getText().toString());
        if (isEmpty(durationEdit))
            duration = 0;
        else
            duration = Integer.parseInt(durationEdit.getText().toString());

        week.addMovement(new Movement(day, name, sets, reps, weight, duration));

        movementCount++;
        refreshTitle();
        resetInputs();
    }

    @SuppressLint("SetTextI18n")
    private void refreshTitle()
    {
        ((TextView)findViewById(R.id.dayTitle))
                .setText(movementCount + "/" + dayLong());
    }

    private void resetInputs()
    {
        nameEdit.getText().clear();
        setsEdit.getText().clear();
        repsEdit.getText().clear();
        weightEdit.getText().clear();
        durationEdit.getText().clear();
    }

    private boolean isEmpty(EditText editText)
    {
        if (editText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }

    private void backToMain()
    {
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }

    private String dayLong()
    {
        String dayName = "Monday";
        switch (this.day)
        {
            case TUE:
                dayName = "Tuesday";
                break;
            case WED:
                dayName = "Wednesday";
                break;
            case THU:
                dayName = "Thursday";
                break;
            case FRI:
                dayName = "Friday";
                break;
            case SAT:
                dayName = "Saturday";
                break;
            case SUN:
                dayName = "Sunday";
                break;
        }
        return dayName;
    }
}