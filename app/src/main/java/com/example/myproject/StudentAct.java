package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentAct  extends AppCompatActivity {
    private EditText stud_enroll, stud_email, stud_pass;
    private Button btn_login;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private String studentID;
    String studEnroll, studEmail, studPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentact);
        getSupportActionBar().setTitle("Student Login");

        stud_enroll = findViewById(R.id.idloginEnroll);
        stud_email = findViewById(R.id.idloginUsername);
        stud_pass = findViewById(R.id.idloginPassword);
        btn_login = findViewById(R.id.idlogin);

        databaseReference = FirebaseDatabase.getInstance().getReference("Students");
           btn_login.setOnClickListener(v -> {
                 studEnroll = stud_enroll.getText().toString().trim();
                 studEmail = stud_email.getText().toString().trim();
                 studPass = stud_pass.getText().toString().trim();
                 studentID = studEnroll;

                 if(studEnroll.isEmpty() || studEmail.isEmpty() || studPass.isEmpty()) {
                     Toast.makeText(StudentAct.this, "Please fill all the credentials", Toast.LENGTH_SHORT).show();
                 }

                 databaseReference.child(studentID).addValueEventListener(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot snapshot) {

                         if(snapshot.exists()) {
                             StudentModal studentModal = snapshot.getValue(StudentModal.class);
                                 if(studentModal.getStudentEnroll().equals(studEnroll)
                                         &&studentModal.getStudentEmail().equals(studEmail)
                                         && studentModal.getStudentPassword().equals(studPass)) {
                                     // on below line we are displaying a toast message.
                                     Toast.makeText(StudentAct.this, "Logging in..", Toast.LENGTH_SHORT).show();
                                     // on below line we are opening our EditCourseActivity on below line.
                                     Intent i = new Intent(StudentAct.this, StudentDashboard.class);
                                     i.putExtra("StudentDash", studentModal);
                                     stud_enroll.setText(null);
                                     stud_email.setText(null);
                                     stud_pass.setText(null);
                                     startActivity(i);
                                     overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                 } else {
                                     Toast.makeText(StudentAct.this, "Login failed..Please check your credentials", Toast.LENGTH_SHORT).show();
                                 }
                             }
                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError error) {
                         Toast.makeText(StudentAct.this, "No such student exists", Toast.LENGTH_SHORT).show();
                     }
                 });
             });
    }



    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
