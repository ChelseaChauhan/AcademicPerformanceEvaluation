package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class FacultyDashone extends AppCompatActivity {
Button regStudent, addRecord, viewRecords, viewStudents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_dashone);
        getSupportActionBar().setTitle("Dashboard");
        regStudent = findViewById(R.id.regStudent);
        addRecord = findViewById(R.id.addRecord);
        viewRecords = findViewById(R.id.viewRecord);
        viewStudents = findViewById(R.id.viewStudent);


        regStudent.setOnClickListener(v -> {
            Intent intent = new Intent(FacultyDashone.this,AddStudent.class );
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        addRecord.setOnClickListener(v -> {
            Intent intent = new Intent(FacultyDashone.this,Marksheet.class );
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        viewRecords.setOnClickListener(v -> {
            Intent i = new Intent(FacultyDashone.this, BeneficiaryListActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        viewStudents.setOnClickListener(v -> {
            Intent i = new Intent(FacultyDashone.this, FacultyDashboard.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menulogout, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(FacultyDashone.this, MainActivity.class);
        Toast.makeText(FacultyDashone.this, "Signing Out..", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}