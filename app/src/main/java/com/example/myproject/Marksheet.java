package com.example.myproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.sql.DatabaseHelperr;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Marksheet extends AppCompatActivity {

    private EditText emailedt, enrolledt, subject1edt, subject2edt,
            subject3edt, subject4edt, subject5edt, marks1edt, marks2edt,
            marks3edt, marks4edt, marks5edt, attdedt, practedt, percentedt, gradeedt;
    Spinner semedt;
    private Button ButtonCalculate;
    private DatabaseHelperr databaseHelper;
    private Button ButtonSelect;
    private Button ButtonRegister;
    int count=0;
    private List<String> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marksheet);
        getSupportActionBar().setTitle("Add Record");

        initViews();
        ButtonSelect.setOnClickListener(v -> {

            list = new ArrayList<>();
            final boolean[] selected = new boolean[31];

            // Create an alert builder
            AlertDialog.Builder builder = new AlertDialog.Builder(Marksheet.this);
            builder.setTitle("Select 5 subjects: ");
            builder.setCancelable(false);
            builder.setMultiChoiceItems(R.array.Subjects, selected, (dialog, which, isChecked) -> {
                count += isChecked ? 1 : -1;
                selected[which] = isChecked;

                if (((AlertDialog) dialog).getListView().getCheckedItemCount() > 5) {
                    Toast.makeText(Marksheet.this, "Limit exceeded..", Toast.LENGTH_SHORT).show();
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
    }

    //Initialize Views
    private void initViews() {
        emailedt = findViewById(R.id.idemail);
        semedt = findViewById(R.id.idsem);
        enrolledt = findViewById(R.id.idenroll);
        subject1edt = findViewById(R.id.subject1);
        subject2edt = findViewById(R.id.subject2);
        subject3edt = findViewById(R.id.subject3);
        subject4edt = findViewById(R.id.subject4);
        subject5edt = findViewById(R.id.subject5);
        marks1edt = findViewById(R.id.marks1);
        marks2edt = findViewById(R.id.marks2);
        marks3edt = findViewById(R.id.marks3);
        marks4edt = findViewById(R.id.marks4);
        marks5edt = findViewById(R.id.marks5);
        attdedt = findViewById(R.id.attendance);
        practedt = findViewById(R.id.practical);
        percentedt = findViewById(R.id.percent);
        gradeedt = findViewById(R.id.grade);

        ButtonRegister = findViewById(R.id.btnSubmit);

        ButtonCalculate = findViewById(R.id.btnCalculate);
        ButtonSelect = findViewById(R.id.btnSelect);

        databaseHelper = new DatabaseHelperr(Marksheet.this);


        ButtonCalculate.setOnClickListener(v -> {
            float num1 = Float.parseFloat(marks1edt.getText().toString());
            float num2 = Float.parseFloat(marks2edt.getText().toString());
            float num3 = Float.parseFloat(marks3edt.getText().toString());
            float num4 = Float.parseFloat(marks4edt.getText().toString());
            float num5 = Float.parseFloat(marks5edt.getText().toString());
            float num6 = Float.parseFloat(practedt.getText().toString());
            float num7 = Float.parseFloat(attdedt.getText().toString());


            if(num1>100 || num2>100 || num3>100 || num4>100 || num5>100 || num6>50 || num7>50) {
                Toast.makeText(Marksheet.this, "Please enter correct marks..", Toast.LENGTH_SHORT).show();
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

        ButtonRegister.setOnClickListener(v -> {
            String email, enroll, sem, sub1, sub2, sub3,
                    sub4, sub5, mar1, mar2, mar3, mar4, mar5,
                    marprac, marattd, perc, grad;

            email = emailedt.getText().toString();
            enroll = enrolledt.getText().toString();
            sem = semedt.getSelectedItem().toString();
            sub1 = subject1edt.getText().toString();
            sub2 = subject2edt.getText().toString();
            sub3 = subject3edt.getText().toString();
            sub4 = subject4edt.getText().toString();
            sub5 = subject5edt.getText().toString();
            mar1 = marks1edt.getText().toString();
            mar2 = marks2edt.getText().toString();
            mar3 = marks3edt.getText().toString();
            mar4 = marks4edt.getText().toString();
            mar5 = marks5edt.getText().toString();
            marprac = practedt.getText().toString();
            marattd = attdedt.getText().toString();
            perc = percentedt.getText().toString();
            grad = gradeedt.getText().toString();

            if (email.isEmpty() || enroll.isEmpty() || sem.isEmpty()
                    || sub1.isEmpty() || sub2.isEmpty() || sub3.isEmpty() || sub4.isEmpty() || sub5.isEmpty()
                    || mar1.isEmpty() || mar2.isEmpty() || mar3.isEmpty() || mar4.isEmpty() || mar5.isEmpty()
                    || marprac.isEmpty() || marattd.isEmpty() || perc.isEmpty() || grad.isEmpty()) {
                Toast.makeText(Marksheet.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                return;
            }

            databaseHelper.addNewBeneficiary(email, enroll, sem, sub1, sub2, sub3, sub4, sub5,
                    mar1, mar2, mar3, mar4, mar5, marprac, marattd, perc, grad);
            // after adding the data we are displaying a toast message.
            Toast.makeText(Marksheet.this, "Record has been added.", Toast.LENGTH_SHORT).show();
            emailedt.setText(null);
            semedt.setSelected(false);
            enrolledt.setText(null);
            subject1edt.setText(null);
            subject2edt.setText(null);
            subject3edt.setText(null);
            subject4edt.setText(null);
            subject5edt.setText(null);
            marks1edt.setText(null);
            marks2edt.setText(null);
            marks3edt.setText(null);
            marks4edt.setText(null);
            marks5edt.setText(null);
            practedt.setText(null);
            attdedt.setText(null);
            percentedt.setText(null);
            gradeedt.setText(null);
        });

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
