package com.example.project_420;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 * Shows sleep,weight and progression graphs
 * @author Niklas Malmgren
 * version 1.1
 */

public class ProgressionGraphActivity extends AppCompatActivity {

    private SharedPreferences weightPrefs, sleepPrefs, workoutPrefs;
    int i, weightIndex, sleepIndex, workoutIndex;
    float sleepTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progression_graph);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.app_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    public void updateUI () {
        final RadioGroup rg = findViewById(R.id.radioGroup);
        GraphView graphView = findViewById(R.id.GraphView);

        if(rg.getCheckedRadioButtonId()==R.id.performanceButton) {
            graphView.removeAllSeries();

            workoutPrefs = getSharedPreferences("workoutPrefs", Context.MODE_PRIVATE);
            workoutIndex = workoutPrefs.getInt("size",0);

            LineGraphSeries<DataPoint> series = new LineGraphSeries<>();

            graphView.getViewport().setXAxisBoundsManual(true);
            graphView.getViewport().setMaxX(workoutIndex);

            for (i = 0; i < workoutIndex; i++) {
                series.appendData(new DataPoint(i,workoutPrefs.getInt(Integer.toString(i),0)),true,9999);
            }

        graphView.setTitle("Workout score");
        graphView.setTitleColor(R.color.purple_700);
        graphView.setTitleTextSize(30);
        graphView.addSeries(series);

    }
        else if(rg.getCheckedRadioButtonId()==R.id.weightButton) {
            graphView.removeAllSeries();

            weightPrefs = getSharedPreferences("weightPrefs", Context.MODE_PRIVATE);
            weightIndex = weightPrefs.getInt("size",0);

            LineGraphSeries<DataPoint> series = new LineGraphSeries<>();

            graphView.getViewport().setXAxisBoundsManual(true);
            graphView.getViewport().setMaxX(weightIndex);

            for (i = 0; i < weightIndex; i++) {
                series.appendData(new DataPoint(i,weightPrefs.getFloat(Integer.toString(i),0)),true,9999);
            }

            graphView.setTitle("Weight");
            graphView.setTitleColor(R.color.purple_700);
            graphView.setTitleTextSize(30);
            graphView.addSeries(series);

    }
        else if (rg.getCheckedRadioButtonId()==R.id.sleepButton) {
            graphView.removeAllSeries();

            sleepPrefs = getSharedPreferences("sleepPrefs", Context.MODE_PRIVATE);
            sleepIndex = sleepPrefs.getInt("size",0);

            LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
            LineGraphSeries<DataPoint> sleepGoal = new LineGraphSeries<>();

            graphView.getViewport().setXAxisBoundsManual(true);
            graphView.getViewport().setMaxX(sleepIndex);

            for (i = 0; i < sleepIndex; i++) {
                series.appendData(new DataPoint(i,sleepPrefs.getFloat(Integer.toString(i),0)),true,9999);
            }

            SharedPreferences gymlogPrefs = getSharedPreferences("gymlogPrefs", Context.MODE_PRIVATE);
            sleepTarget = gymlogPrefs.getFloat("sleepTargetPref", 8);
            int sleepTargetIndex =+ sleepIndex + 1;

            for (i = 0; i < sleepTargetIndex; i++) {
                sleepGoal.appendData(new DataPoint(i,sleepTarget),true,9999);       //sleep goal reference line
            }

            sleepGoal.setColor(Color.GREEN);
            graphView.setTitle("Sleep");
            graphView.setTitleColor(R.color.purple_700);
            graphView.setTitleTextSize(30);
            graphView.addSeries(series);
            graphView.addSeries(sleepGoal);

        }
}

        public void buttonPressed(View view) {
        updateUI();
        }
}