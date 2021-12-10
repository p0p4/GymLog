package com.example.project_420;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);

        listView.setAdapter(arrayAdapter);
    }
}