package com.example.project_420;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

/**
 * Class for the week plan creation activity.
 * @author Tino Behnen
 * @version 1.2
 */
public class PlanWeek extends AppCompatActivity
{
    private static final String TAG = "PlanWeek";
    private Week week;
    private Day day = Day.MON;
    private int movementCount = 1;
    private EditText nameEdit, setsEdit, repsEdit, weightEdit, durationEdit;

    /**
     * Sets an app logo for the activity actionbar.
     * Deletes previously saved week plan, {@link Week#clear()}.
     * Validates user input with {@link #validateInput(EditText)}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_week);

        Log.d(TAG, "onCreate() - called");

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.app_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        week = Week.getInstance();
        week.clear();
        week.save(this);
        week.load(this);

        nameEdit = findViewById(R.id.nameInput);
        setsEdit = findViewById(R.id.setsInput);
        repsEdit = findViewById(R.id.repsInput);
        weightEdit = findViewById(R.id.weightInput);
        durationEdit = findViewById(R.id.durationInput);

        validateInput(setsEdit);
        validateInput(repsEdit);
        validateInput(weightEdit);
        validateInput(durationEdit);

        refreshTitle();
    }

    /**
     * Exits week planning & saves the user defined movement.
     * @param view Executes when the "end week" button in the activity is pressed.
     */
    public void endWeek(View view)
    {
        newMovement();
        backToMain();
    }

    /**
     * Moves to the next day & saves the user defined movement.
     * Resets movement creation counter.
     * Return back to the main activity if the activity is on sunday.
     * @param view Executes when the "end day" button in the activity is pressed.
     */
    public void endDay(View view)
    {
        newMovement();
        movementCount = 1;

        if (day == Day.SUN)
            backToMain();
        else {
            day = Day.values()[day.ordinal() + 1];
            refreshTitle();
        }
    }

    /**
     * Saves the user defined movement.
     * Gives a warning to the user if a required user input field is empty.
     * @param view Executes when the "next move" button in the activity is pressed.
     */
    public void nextMove(View view)
    {
        if (isEmpty(nameEdit) || isEmpty(setsEdit) || isEmpty(repsEdit))
            Toast.makeText(this, "Fill in required* fields!", Toast.LENGTH_LONG).show();

        newMovement();
    }

    /**
     * Validates user a defined object & adds it to the object array in {@link Week}.
     * Sets optional user inputs to 0 if {@code null}.
     */
    private void newMovement()
    {
        if (isEmpty(nameEdit) || isEmpty(setsEdit) || isEmpty(repsEdit))
            return;

        String name = nameEdit.getText().toString();
        int sets = Integer.parseInt(setsEdit.getText().toString());
        int reps = Integer.parseInt(repsEdit.getText().toString());

        int weight;
        if (isEmpty(weightEdit))
            weight = 0;
        else
            weight = Integer.parseInt(weightEdit.getText().toString());

        int duration;
        if (isEmpty(durationEdit))
            duration = 0;
        else
            duration = Integer.parseInt(durationEdit.getText().toString());

        week.addMovement(new Movement(day, name, sets, reps, weight, duration));

        movementCount++;
        refreshTitle();
        resetInputs();
    }

    /**
     * Updates the activity title with the amount of movements created and for which weekday.
     */
    @SuppressLint("SetTextI18n")
    private void refreshTitle()
    {
        ((TextView) findViewById(R.id.dayTitle))
                .setText(movementCount + "/" + dayLong());
    }

    /**
     * Clears any values in the user input fields of the activity.
     */
    private void resetInputs()
    {
        nameEdit.getText().clear();
        setsEdit.getText().clear();
        repsEdit.getText().clear();
        weightEdit.getText().clear();
        durationEdit.getText().clear();
    }

    /**
     * Checks if a user input field is empty or not.
     * @param editText User input field.
     * @return true if the input parameters Edittext is empty, else false
     */
    private boolean isEmpty(EditText editText)
    {
        return editText.getText().toString().trim().length() <= 0;
    }

    /**
     * Moves to the {@link MainActivity} activity.
     */
    private void backToMain()
    {
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }

    /**
     * Converts enum {@link Day} to String form.
     * @return String version of the day that the activity is currently on.
     */
    private String dayLong()
    {
        String dayName = "Monday";
        switch (this.day) {
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

    /**
     * Validates user input characters.
     * @param editText User input field.
     */
    private void validateInput(EditText editText)
    {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                String input = editText.getText().toString();
                boolean enabled = !input.contains(" ") && !input.contains(",") && !input.contains("-") && !input.contains(".") && !input.contains("0");

                (findViewById(R.id.btnNextMove)).setEnabled(enabled);
                (findViewById(R.id.btnEndDay)).setEnabled(enabled);
                (findViewById(R.id.btnEndWeek)).setEnabled(enabled);

                if (!enabled)
                    editText.setError("Invalid input!");
            }
        });
    }

    /**
     * Saves the object array to local storage with {@link Week#save(Context)}.
     */
    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d(TAG, "onPause() - called");

        week.save(this);
    }
}