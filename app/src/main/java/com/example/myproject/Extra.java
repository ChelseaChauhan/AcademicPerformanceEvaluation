package com.example.myproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.model.Beneficiary;
import com.example.myproject.sql.DatabaseHelperr;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;


public class Extra extends AppCompatActivity {
    DatabaseHelperr databaseHelper;
    String email;
    TextView textView;
    LineChart lineChart;
    LineData lineData;
    LineDataSet lineDataSet;
    ArrayList<Entry> lineEntries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra);
        getSupportActionBar().setTitle("Progress Chart");
        textView = findViewById(R.id.textView2);
        lineChart = findViewById(R.id.idGraphView);


        databaseHelper= new DatabaseHelperr(Extra.this);
        email = getIntent().getStringExtra("EMAIL");
        getDataValues();

        lineDataSet = new LineDataSet(lineEntries, "Semesters");

        lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        lineDataSet.setValueTextColor(Color.WHITE);
        lineDataSet.setValueTextSize(14f);
        lineDataSet.setLineWidth(3f);
        lineChart.getDescription().setEnabled(false);
        lineChart.animateY(1500);
        lineDataSet.setCircleRadius(5f);
        lineDataSet.setCircleHoleRadius(2f);
        lineDataSet.setCircleColor(Color.BLACK);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setDrawGridLines(false);


    }


    private void getDataValues() {
        databaseHelper= new DatabaseHelperr(Extra.this);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String[] params = new String[]{ email };
        lineEntries= new ArrayList<>();

        String ans2=" ";

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorRecords = db.rawQuery("SELECT * FROM myrecords WHERE EMAIL=?",params);

        int i=1;
            if( cursorRecords.moveToFirst()) {
                do {
                    ans2 = cursorRecords.getString(cursorRecords.getColumnIndex("PERCENT"));
                    float ans = Float.parseFloat(ans2);
                    lineEntries.add(new Entry(i, ans));
                    i=i+1;
                }
              while(cursorRecords.moveToNext());

      }
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}

