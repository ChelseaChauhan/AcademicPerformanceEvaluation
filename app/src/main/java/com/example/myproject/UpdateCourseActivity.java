package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myproject.sql.DatabaseHelperr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpdateCourseActivity extends AppCompatActivity {
    private EditText emailedt, enrolledt, subject1edt, subject2edt,
            subject3edt, subject4edt, subject5edt, marks1edt, marks2edt,
            marks3edt, marks4edt, marks5edt, attdedt, practedt, percentedt, gradeedt;
    private Spinner semedt;
    private Button updateCourseBtn, ButtonCalculate, deleteCourseBtn, ButtonSelect;
    private DatabaseHelperr databaseHelper;
    String email, enroll, sem, sub1, sub2, sub3,
            sub4, sub5, mar1, mar2, mar3, mar4, mar5,
            marprac, marattd, perc, grad;
    int count=0;
    private List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_course);
        getSupportActionBar().setTitle("Update Record");

        emailedt =  findViewById(R.id.idemail);
        semedt = findViewById(R.id.idsem);
        enrolledt = findViewById(R.id.idenroll);
        subject1edt = findViewById(R.id.subject1);
        subject2edt =  findViewById(R.id.subject2);
        subject3edt =  findViewById(R.id.subject3);
        subject4edt = findViewById(R.id.subject4);
        subject5edt =  findViewById(R.id.subject5);
        marks1edt = findViewById(R.id.marks1);
        marks2edt =  findViewById(R.id.marks2);
        marks3edt =  findViewById(R.id.marks3);
        marks4edt = findViewById(R.id.marks4);
        marks5edt = findViewById(R.id.marks5);
        attdedt = findViewById(R.id.attendance);
        practedt = findViewById(R.id.practical);
        percentedt = findViewById(R.id.percent);
        gradeedt =  findViewById(R.id.grade);
        updateCourseBtn =  findViewById(R.id.btnUpdate);
        ButtonCalculate = findViewById(R.id.btnCalculate);
        deleteCourseBtn = findViewById(R.id.btnDelete);
        ButtonSelect = findViewById(R.id.btnSelect);
        // on below line we are initialing our dbhandler class.
        databaseHelper = new DatabaseHelperr(UpdateCourseActivity.this);


        // on below lines we are getting data which
        // we passed in our adapter class.
        email = getIntent().getStringExtra("EMAIL");
        enroll = getIntent().getStringExtra("ENROLLMENT");
        sem = getIntent().getStringExtra("SEMESTER");
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
        marprac = getIntent().getStringExtra("PRACTICAL");
        marattd = getIntent().getStringExtra("ATTENDANCE");
        perc = getIntent().getStringExtra("PERCENT");
        grad = getIntent().getStringExtra("GRADE");

        // setting data to edit text
        // of our update activity.
        emailedt.setText(email);
        enrolledt.setText(enroll);
        semedt.setSelected(true);
        subject1edt.setText(sub1);
        subject2edt.setText(sub2);
        subject3edt.setText(sub3);
        subject4edt.setText(sub4);
        subject5edt.setText(sub5);
        marks1edt.setText(mar1);
        marks2edt.setText(mar2);
        marks3edt.setText(mar3);
        marks4edt.setText(mar4);
        marks5edt.setText(mar5);
        practedt.setText(marprac);
        attdedt.setText(marattd);
        percentedt.setText(perc);
        gradeedt.setText(grad);

        ButtonSelect.setOnClickListener(v -> {

            list = new ArrayList<>();
            final boolean[] selected = new boolean[31];

            // Create an alert builder
            AlertDialog.Builder builder = new AlertDialog.Builder(UpdateCourseActivity.this);
            builder.setTitle("Select 5 subjects: ");
            builder.setCancelable(false);
            builder.setMultiChoiceItems(R.array.Subjects, selected, (dialog, which, isChecked) -> {
                count += isChecked ? 1 : -1;
                selected[which] = isChecked;

                if (((AlertDialog) dialog).getListView().getCheckedItemCount() > 5) {
                    Toast.makeText(UpdateCourseActivity.this, "Limit exceeded..", Toast.LENGTH_SHORT).show();
                    selected[which] = false;
                    count--;
                    ((AlertDialog) dialog).getListView().setItemChecked(which, false);
                }
            });
            builder.setPositiveButton("DONE", (dialog, which) -> {
                String arr[] = getResources().getStringArray(R.array.Subjects);
                ArrayList<EditText> emptyFields = new ArrayList<>(Arrays.asList(subject1edt, subject2edt, subject3edt, subject4edt, subject5edt));
                for (int i = 0; i < 31; i++) {
                    if (selected[i]) {
                        list.add(arr[i]);
                    }
                }
                for (int j = 0; j < list.size(); j++) {
                    emptyFields.get(j).setText(list.get(j));
                }

            });
            builder.setNegativeButton("CANCEL", (dialog, which) -> {

            });

            // create and show
            // the alert dialog
            AlertDialog dialog = builder.create();
            dialog.show();
        });

        ButtonCalculate.setOnClickListener(v -> {
            float num1 = Float.parseFloat(marks1edt.getText().toString());
            float num2 = Float.parseFloat(marks2edt.getText().toString());
            float num3 = Float.parseFloat(marks3edt.getText().toString());
            float num4 = Float.parseFloat(marks4edt.getText().toString());
            float num5 = Float.parseFloat(marks5edt.getText().toString());
            float num6 = Float.parseFloat(practedt.getText().toString());
            float num7 = Float.parseFloat(attdedt.getText().toString());


            if(num1>100 || num2>100 || num3>100 || num4>100 || num5>100 || num6>50 || num7>50) {
                Toast.makeText(UpdateCourseActivity.this, "Please enter correct marks..", Toast.LENGTH_SHORT).show();
            }
            else {
                float result = (num1 + num2 + num3 + num4 + num5 + num6 + num7) / 6;

                percentedt.setText(Float.toString(result));

                if (result > 90 && result <= 100)
                    gradeedt.setText("A1");
                else if (result > 80 && result <= 90)
                    gradeedt.setText("A2");
                else if (result > 70 && result <= 80)
                    gradeedt.setText("B1");
                else if (result > 60 && result <= 70)
                    gradeedt.setText("B2");
                else if (result > 50 && result <= 60)
                    gradeedt.setText("C1");
                else if (result > 40 && result <= 50)
                    gradeedt.setText("C2");
                else if (result > 32 && result <= 40)
                    gradeedt.setText("D");
                else
                    gradeedt.setText("Fail");
            }
        });
        // adding on click listener to our update course button.
        updateCourseBtn.setOnClickListener(v -> {

            // inside this method we are calling an update course
            // method and passing all our edit text values.
            databaseHelper.updateBeneficiary(email, sem, emailedt.getText().toString(), enrolledt.getText().toString(), semedt.getSelectedItem().toString(),
                    subject1edt.getText().toString(), subject2edt.getText().toString(), subject3edt.getText().toString(), subject4edt.getText().toString(), subject5edt.getText().toString(),
                    marks1edt.getText().toString(), marks2edt.getText().toString(), marks3edt.getText().toString(), marks4edt.getText().toString(), marks5edt.getText().toString(),
                    practedt.getText().toString(), attdedt.getText().toString(), percentedt.getText().toString(), gradeedt.getText().toString());

            // displaying a toast message that our course has been updated.
            Toast.makeText(UpdateCourseActivity.this, "Record Updated..", Toast.LENGTH_SHORT).show();

            // launching our main activity.
            Intent i = new Intent(UpdateCourseActivity.this, BeneficiaryListActivity.class);
            startActivity(i);
            finish();
        });

        // adding on click listener for delete button to delete our course.
        deleteCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to delete our course.
                databaseHelper.deleteBeneficiary(email,sem);
                Toast.makeText(UpdateCourseActivity.this, "Deleted the Record..", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateCourseActivity.this, BeneficiaryListActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}

