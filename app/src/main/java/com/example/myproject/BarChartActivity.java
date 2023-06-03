package com.example.myproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.sql.DatabaseHelperr;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity {
    DatabaseHelperr databaseHelper;
    String sub1, sub2, sub3, sub4, sub5, mar1, mar2, mar3, mar4, mar5;
    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList barEntriesArrayList;
    ArrayList subjectList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        getSupportActionBar().setTitle("Marks Chart");

        // initializing variable for bar chart.
        barChart = findViewById(R.id.idBarChart);
        // calling method to get bar entries.
        getBarEntries();
        // creating a new bar data set.
        barDataSet = new BarDataSet(barEntriesArrayList, "Marks");
        // creating a new bar data and
        // passing our bar data set.
        barData = new BarData(barDataSet);
        // below line is to set data
        // to our bar chart.
        barChart.setData(barData);
        // adding color to our bar data set.
        barDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
        // setting text color.
        barDataSet.setValueTextColor(Color.WHITE);
        // setting text size
        barDataSet.setValueTextSize(14f);
        barChart.getDescription().setEnabled(false);

    }


    private void getBarEntries() {
// on below line we are initialing our dbhandler class.
        databaseHelper = new DatabaseHelperr(BarChartActivity.this);
        sub1 = getIntent().getStringExtra("SUBJECT1");
        sub2 = getIntent().getStringExtra("SUBJECT2");
        sub3 = getIntent().getStringExtra("SUBJECT3");
        sub4 = getIntent().getStringExtra("SUBJECT4");
        sub5 = getIntent().getStringExtra("SUBJECT5");
        mar1 = getIntent().getStringExtra("MARKS1");
        mar2 = getIntent().getStringExtra("MARKS2");
        mar3 = getIntent().getStringExtra("MARKS3");
        mar4 = getIntent().getStringExtra("MARKS4");
        mar5 = getIntent().getStringExtra("MARKS5");

        // creating a new array list
        barEntriesArrayList = new ArrayList<>();

        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        barEntriesArrayList.add(new BarEntry(0.6f, Float.parseFloat(mar1)));
        barEntriesArrayList.add(new BarEntry(1.6f, Float.parseFloat(mar2)));
        barEntriesArrayList.add(new BarEntry(2.6f, Float.parseFloat(mar3)));
        barEntriesArrayList.add(new BarEntry(3.6f, Float.parseFloat(mar4)));
        barEntriesArrayList.add(new BarEntry(4.6f, Float.parseFloat(mar5)));


        subjectList = new ArrayList();

        subjectList.add(sub1);
        subjectList.add(sub2);
        subjectList.add(sub3);
        subjectList.add(sub4);
        subjectList.add(sub5);



        Legend l = barChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(true);
        l.setYOffset(20f);
        l.setXOffset(0f);
        l.setYEntrySpace(0f);
        l.setTextSize(10f);

// below line is to get x axis
        // of our bar chart.
        XAxis xAxis = barChart.getXAxis();
        // below line is to set value formatter to our x-axis and
        // we are adding our days to our x axis.
        xAxis.setValueFormatter(new IndexAxisValueFormatter(subjectList));

        // below line is to set center axis
        // labels to our bar chart.
        xAxis.setCenterAxisLabels(true);
        // below line is to set position
        // to our x-axis to bottom.
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // below line is to set granularity
        // to our x axis labels.
        xAxis.setGranularity(1);
        xAxis.setDrawGridLines(false);
        // below line is to enable
        // granularity to our x axis.
        xAxis.setGranularityEnabled(true);
        // below line is to make our
        // bar chart as draggable.
        barChart.setDragEnabled(true);
        // below line is to make visible
        // range for our bar chart.
        barChart.setVisibleXRangeMaximum(3);
        // below line is to set minimum
        // axis to our chart.
        barChart.getXAxis().setAxisMinimum(0);
        // below line is to
        // animate our chart.
        barChart.animateY(1500);
        // below line is to invalidate
        // our bar chart.
        barChart.invalidate();
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}

