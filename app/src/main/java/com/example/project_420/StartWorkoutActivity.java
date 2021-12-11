package com.example.project_420;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Shows today's workout in a listview
 * @author Niklas Malmgren
 * version 1.1
 */

public class StartWorkoutActivity extends AppCompatActivity {

    String weekday = LocalDate.now().getDayOfWeek().name();
    ListView listView;
    Week week;
    int workoutScore,i,workoutIndex;
    SharedPreferences gymlogPrefs;

    ArrayList<Integer> workoutList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_workout);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.app_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        TextView textView = findViewById(R.id.Day);
        textView.setText(weekday);

        week = Week.getInstance();
        week.load(this);

        listView = (ListView) findViewById(R.id.listview);

        ArrayList<String> arrayList = new ArrayList<>();

        if (weekday.equals("MONDAY")) {
            arrayList.clear();
            for (int i = 0; i < week.getDay(Day.MON).size(); i++) {
                arrayList.add(week.getDay(Day.MON).get(i).toString());
            }
        } else if (weekday.equals("TUESDAY")) {
            arrayList.clear();
            for (int i = 0; i < week.getDay(Day.TUE).size(); i++) {
                arrayList.add(week.getDay(Day.TUE).get(i).toString());
            }
        } else if (weekday.equals("WEDNESDAY")) {
            arrayList.clear();
            for (int i = 0; i < week.getDay(Day.WED).size(); i++) {
                arrayList.add(week.getDay(Day.WED).get(i).toString());
            }
        } else if (weekday.equals("THURSDAY")) {
            arrayList.clear();
            for (int i = 0; i < week.getDay(Day.THU).size(); i++) {
                arrayList.add(week.getDay(Day.THU).get(i).toString());
            }
        } else if (weekday.equals("FRIDAY")) {
            arrayList.clear();
            for (int i = 0; i < week.getDay(Day.FRI).size(); i++) {
                arrayList.add(week.getDay(Day.FRI).get(i).toString());
            }
        } else if (weekday.equals("SATURDAY")) {
            arrayList.clear();
            for (int i = 0; i < week.getDay(Day.SAT).size(); i++) {
                arrayList.add(week.getDay(Day.SAT).get(i).toString());
            }
        } else if (weekday.equals("SUNDAY")) {
            arrayList.clear();
            for (int i = 0; i < week.getDay(Day.SUN).size(); i++) {
                arrayList.add(week.getDay(Day.SUN).get(i).toString());
            }
        }

        if (arrayList.size()==0) {
            arrayList.add("Today is a rest day!");
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);

        listView.setAdapter(arrayAdapter);

        gymlogPrefs = getSharedPreferences("gymlogPrefs", Context.MODE_PRIVATE);
        workoutScore = gymlogPrefs.getInt("workoutScore",0);

        SharedPreferences workoutPrefs = getSharedPreferences("workoutPrefs", Context.MODE_PRIVATE);
        workoutIndex = workoutPrefs.getInt("size",0);
        for(i = 0; i < workoutIndex; i++) {
            workoutList.add(workoutPrefs.getInt(Integer.toString(i), 0));
        }
    }

    public void successButton (View view) {
        workoutScore++;
        SharedPreferences.Editor gymlogPrefsEditor = gymlogPrefs.edit();
        gymlogPrefsEditor.putInt("workoutScore",workoutScore);
        gymlogPrefsEditor.apply();

        workoutList.add(workoutScore);
        SharedPreferences workoutPrefs = getSharedPreferences("workoutPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = workoutPrefs.edit();
        for (i = 0; i < workoutList.size(); i++) {
            editor.putInt(Integer.toString(i), workoutList.get(i));
        }
        editor.putInt("size", workoutList.size());
        editor.apply();

        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }

    public void partButton (View view) {
        workoutList.add(workoutScore);
        SharedPreferences workoutPrefs = getSharedPreferences("workoutPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = workoutPrefs.edit();
        for (i = 0; i < workoutList.size(); i++) {
            editor.putInt(Integer.toString(i), workoutList.get(i));
        }
        editor.putInt("size", workoutList.size());
        editor.apply();

        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }

    public void failButton (View view) {
        workoutScore--;
        SharedPreferences.Editor gymlogPrefsEditor = gymlogPrefs.edit();
        gymlogPrefsEditor.putInt("workoutScore",workoutScore);
        gymlogPrefsEditor.apply();

        workoutList.add(workoutScore);
        SharedPreferences workoutPrefs = getSharedPreferences("workoutPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = workoutPrefs.edit();
        for (i = 0; i < workoutList.size(); i++) {
            editor.putInt(Integer.toString(i), workoutList.get(i));
        }
        editor.putInt("size", workoutList.size());
        editor.apply();

        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }
}