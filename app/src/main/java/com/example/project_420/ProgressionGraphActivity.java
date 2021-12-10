package com.example.project_420;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 * Shows sleep,weight and progression graphs
 * @author Niklas Malmgren
 * version 1.0
 */

public class ProgressionGraphActivity extends AppCompatActivity {

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
                new DataPoint(0, 1),
                new DataPoint(1, 3),
                new DataPoint(2, -5),
                new DataPoint(3, 9),
                new DataPoint(4, -7),
                new DataPoint(5, 3),
                new DataPoint(6, 6),
                new DataPoint(7, 16),
                new DataPoint(8, 129),
        });

        graphView.setTitle("Progression");

        graphView.setTitleColor(R.color.purple_700);

        graphView.setTitleTextSize(30);

        graphView.addSeries(series);

    }
        else if(rg.getCheckedRadioButtonId()==R.id.weightButton) {
            graphView.removeAllSeries();

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(0, 80),
                new DataPoint(1, 85),
                new DataPoint(2, 90),
                new DataPoint(3, 95),
                new DataPoint(4, 85),
                new DataPoint(5, 80),
                new DataPoint(6, 70),
                new DataPoint(7, 90),
                new DataPoint(8, 100),
        });

        graphView.setTitle("Weight");

        graphView.setTitleColor(R.color.purple_700);

        graphView.setTitleTextSize(30);

        graphView.addSeries(series);

    }
        else if (rg.getCheckedRadioButtonId()==R.id.sleepButton) {
            graphView.removeAllSeries();

            LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{

                    new DataPoint(0, 7),
                    new DataPoint(1, 9),
                    new DataPoint(2, 4),
                    new DataPoint(3, 12),
                    new DataPoint(4, 6),
                    new DataPoint(5, 9),
                    new DataPoint(6, 8),
                    new DataPoint(7, 2),
                    new DataPoint(8, 10),
            });

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