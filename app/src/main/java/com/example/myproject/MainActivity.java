package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button faculty;
    private Button student;
    private Button admin;
    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        faculty=(Button) findViewById(R.id.button);
        student=(Button) findViewById(R.id.button2);
        admin = (Button) findViewById(R.id.button3);
        faculty.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FacultyLogin.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
        student.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, StudentAct.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
        admin.setOnClickListener(v ->{
            Intent intent = new Intent(MainActivity.this, AdminActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
    }
    @Override
    public void onBackPressed() {

        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
        }
        else { Toast.makeText(getBaseContext(), "Tap back button in order to exit", Toast.LENGTH_SHORT).show(); }
        mBackPressed = System.currentTimeMillis();
    }
}