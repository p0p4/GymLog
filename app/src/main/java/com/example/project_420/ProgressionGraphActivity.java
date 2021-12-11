package com.example.project_420;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
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

    private SharedPreferences weightPrefs,sleepPrefs;
    int i,weightindex,sleepindex;

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

        if(rg.getCheckedRadioButtonId()==R.id.progressionButton) {
            graphView.removeAllSeries();

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(0, 1.5),
                new DataPoint(1, 3),
                new DataPoint(2, -5),
                new DataPoint(3, 9),
                new DataPoint(4, -7),
                new DataPoint(5, 3),
                new DataPoint(6, 6),
                new DataPoint(7, 16),
                new DataPoint(8, 129),
                new DataPoint(9, 123),
        });

        graphView.setTitle("Progression");
        graphView.setTitleColor(R.color.purple_700);
        graphView.setTitleTextSize(30);
        graphView.addSeries(series);

    }
        else if(rg.getCheckedRadioButtonId()==R.id.weightButton) {
            graphView.removeAllSeries();

            weightPrefs = getSharedPreferences("weightPrefs", Context.MODE_PRIVATE);
            weightindex = weightPrefs.getInt("size",0);

            LineGraphSeries<DataPoint> series = new LineGraphSeries<>();

            graphView.getViewport().setXAxisBoundsManual(true);
            graphView.getViewport().setMaxX(weightindex);

            for (i = 0; i < weightindex;i++) {
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
            sleepindex = sleepPrefs.getInt("size",0);

            LineGraphSeries<DataPoint> series = new LineGraphSeries<>();

            graphView.getViewport().setXAxisBoundsManual(true);
            graphView.getViewport().setMaxX(sleepindex);

            for (i = 0; i < sleepindex;i++) {
                series.appendData(new DataPoint(i,sleepPrefs.getFloat(Integer.toString(i),0)),true,9999);
            }

            graphView.setTitle("Sleep");
            graphView.setTitleColor(R.color.purple_700);
            graphView.setTitleTextSize(30);
            graphView.addSeries(series);

        }
}

        public void buttonPressed(View view) {
        updateUI();
        }
}