package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class AddStudent extends AppCompatActivity {
    private EditText stud_mail, stud_pass, stud_phone, stud_name, stud_img, stud_enroll;
    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private String studentID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addstudent);
        getSupportActionBar().setTitle("Register Student");
        stud_mail = findViewById(R.id.Email);
        stud_pass = findViewById(R.id.pass);
        stud_phone = findViewById(R.id.Phone);
        stud_name = findViewById(R.id.name);
        stud_img = findViewById(R.id.image);
        stud_enroll = findViewById(R.id.enroll);
        Button btn_submit = findViewById(R.id.submit);
        mAuth=FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        // on below line creating our database reference.
        databaseReference = firebaseDatabase.getReference("Students");

        btn_submit.setOnClickListener(v -> {
            addInfo();
        });
    }


    private void addInfo() {
        // getting data from our edit text.
        String studentEmail = stud_mail.getText().toString().trim();
        String studentPassword = stud_pass.getText().toString().trim();
        String studentName = stud_name.getText().toString().trim();
        String studentEnroll = stud_enroll.getText().toString().trim();
        String studentImage = stud_img.getText().toString().trim();
        String studentPhone = stud_phone.getText().toString().trim();
        studentID = studentEnroll;

        if(studentName.isEmpty() || studentEnroll.isEmpty() || studentPhone.isEmpty()) {
            Toast.makeText(AddStudent.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
            return;
        }

        // on below line we are passing all data to our modal class.
        StudentModal studentModal = new StudentModal( studentEmail, studentPassword,  studentName, studentID, studentEnroll, studentImage, studentPhone);
        // on below line we are calling a add value event
        // to pass data to firebase database.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // on below line we are setting data in our firebase database.
                databaseReference.child(studentID).setValue(studentModal);
                // displaying a toast message.
                Toast.makeText(AddStudent.this, "Student Added..", Toast.LENGTH_SHORT).show();
                sendcredentials();
                // starting a main activity.
                startActivity(new Intent(AddStudent.this, FacultyDashboard.class));
            }
            @Override
            public void onCancelled (@NonNull DatabaseError error){
                // displaying a failure message on below line.
               // Toast.makeText(AddStudent.this, "Fail to add student..", Toast.LENGTH_SHORT).show();
                errorMessage();
            }
        });
    }

    public void errorMessage() {
        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText("Something went wrong!").show();
    }

    private void sendcredentials() {
        //sending credentials
        String studentPhone = stud_phone.getText().toString();
        String studentEmail = stud_mail.getText().toString();
        String studentPassword = stud_pass.getText().toString();
        String msg= "CONGRATULATIONS!! You are now successfully registered with APES." + "\n\n"
                + "EMAIL: " + studentEmail + "\n" + "PASSWORD: " + studentPassword;


        try {
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(studentPhone,null,msg,null,null);
            //Toast.makeText(getApplicationContext(),"Message Sent",Toast.LENGTH_LONG).show();
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Some field is Empty",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}