package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditStudentActivity extends AppCompatActivity {
    private EditText stud_mail, stud_phone, stud_name, stud_img, stud_pass, stud_enroll;
    private Button btn_update, btn_delete;
    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private String studentID;
    StudentModal studentModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
        getSupportActionBar().setTitle("Edit Student");
        stud_mail = findViewById(R.id.Email);
        stud_pass = findViewById(R.id.pass);
        stud_phone = findViewById(R.id.Phone);
        stud_name = findViewById(R.id.name);
        stud_img = findViewById(R.id.image);
        stud_enroll = findViewById(R.id.enroll);
        btn_update = findViewById(R.id.update);
        btn_delete = findViewById(R.id.delete);

        stud_mail.setEnabled(false);
        stud_pass.setEnabled(false);

        FloatingActionButton backHome = findViewById(R.id.idbackHome);

        firebaseDatabase = FirebaseDatabase.getInstance();
        // on below line we are getting our modal class on which we have passed.
        studentModal = getIntent().getParcelableExtra("Student");

        if (studentModal != null) {
            // on below line we are setting data to our edit text from our modal class.
            stud_mail.setText(studentModal.getStudentEmail());
            stud_pass.setText(studentModal.getStudentPassword());
            stud_name.setText(studentModal.getStudentName());
            studentID = studentModal.getStudentId();
            stud_enroll.setText(studentModal.getStudentEnroll());
            stud_img.setText(studentModal.getStudentImage());
            stud_phone.setText(studentModal.getStudentPhone());
        }

        // on below line we are initialing our database reference and we are adding a child as our course id.
        databaseReference = firebaseDatabase.getReference("Students").child(studentID);
        // on below line we are adding click listener for our add course button.
        btn_update.setOnClickListener(v -> {
            String studentEmail = stud_mail.getText().toString();
            String studentPassword = stud_pass.getText().toString();
            String studentName = stud_name.getText().toString();
            String studentEnroll = stud_enroll.getText().toString();
            String studentImage = stud_img.getText().toString();
            String studentPhone = stud_phone.getText().toString();

            // on below line we are creating a map for
            // passing a data using key and value pair.
            Map<String, Object> map = new HashMap<>();
            map.put("studentEmail", studentEmail);
            map.put("studentPassword", studentPassword);
            map.put("studentName", studentName);
            map.put("studentId", studentID);
            map.put("studentEnroll", studentEnroll);
            map.put("studentImage", studentImage);
            map.put("studentPhone", studentPhone);



            // on below line we are calling a database reference on
            // add value event listener and on data change method
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    // adding a map to our database.
                    databaseReference.updateChildren(map);
                    // on below line we are displaying a toast message.
                    Toast.makeText(EditStudentActivity.this, "Student Updated..", Toast.LENGTH_SHORT).show();
                    // opening a new activity after updating our coarse.
                    startActivity(new Intent(EditStudentActivity.this, FacultyDashboard.class));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // displaying a failure message on toast.
                    Toast.makeText(EditStudentActivity.this, "Fail to update student..", Toast.LENGTH_SHORT).show();
                }
            });
        });
                btn_delete.setOnClickListener(v -> {
                    // calling a method to delete a course.
                    deleteStudent();
                });

        // on below line adding a click listener for our floating action button.
        backHome.setOnClickListener(v -> {
            // opening a new activity for adding a course.
            Intent i = new Intent(EditStudentActivity.this, FacultyDashone.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });
    }


    private void deleteStudent() {
        // on below line calling a method to delete the course.
        databaseReference.removeValue();
        // displaying a toast message on below line.
        Toast.makeText(this, "Student Record Deleted..", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(EditStudentActivity.this, FacultyDashboard.class));
    }
}