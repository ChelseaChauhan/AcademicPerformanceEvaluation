package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.myproject.adapter.StudentRecordRecyclerAdapter;
import com.example.myproject.model.Beneficiary;
import com.example.myproject.sql.DatabaseHelperr;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class StudentRecordListActivity extends AppCompatActivity {
    private ArrayList<Beneficiary> studentrecordArraylist;
    private DatabaseHelperr databaseHelper;
    private StudentRecordRecyclerAdapter studentRecordRecyclerAdapter;
    private RecyclerView recyclerViewStudentRecord;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_record_list);
        getSupportActionBar().setTitle("My Records");


        studentrecordArraylist = new ArrayList<>();
        databaseHelper = new DatabaseHelperr(StudentRecordListActivity.this);

        email = getIntent().getStringExtra("EMAIL");

        // getting our course array
        // list from db handler class.
        studentrecordArraylist = databaseHelper.readSingleBeneficiary(email);
        // on below line passing our array lost to our adapter class.
        studentRecordRecyclerAdapter = new StudentRecordRecyclerAdapter(studentrecordArraylist, StudentRecordListActivity.this);
        recyclerViewStudentRecord = findViewById(R.id.recyclerViewStudentRecord);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(StudentRecordListActivity.this, RecyclerView.VERTICAL, false);
        recyclerViewStudentRecord.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        recyclerViewStudentRecord.setAdapter(studentRecordRecyclerAdapter);
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}