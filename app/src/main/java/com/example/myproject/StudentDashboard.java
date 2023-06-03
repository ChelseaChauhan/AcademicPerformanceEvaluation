package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.model.Beneficiary;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class StudentDashboard extends AppCompatActivity {

    private TextView name, rollnum, email, pass, phone;
    private ImageView imgStud;
    private Button btn_seeRecord, btn_seeProgress;
    StudentModal studentModal;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String studentID;
    FloatingActionButton backHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);
        getSupportActionBar().setTitle("Student Dashboard");

        name = findViewById(R.id.textViewName);
        rollnum = findViewById(R.id.textViewRoll);
        email = findViewById(R.id.textViewMail);
        pass = findViewById(R.id.textViewPass);
        phone = findViewById(R.id.textViewPhone);
        imgStud = findViewById(R.id.img);
        btn_seeRecord = findViewById(R.id.btnSeeRecord);
        btn_seeProgress = findViewById(R.id.btnSeeProgress);

        backHome = findViewById(R.id.idbackHome);
        // on below line adding a click listener for our floating action button.
        backHome.setOnClickListener(v -> {
            // opening a new activity for adding a course.
            Intent i = new Intent(StudentDashboard.this, MainActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });

        studentModal = getIntent().getParcelableExtra("StudentDash");

        if (studentModal != null) {
            name.setText(studentModal.getStudentName());
            rollnum.setText(studentModal.getStudentEnroll());
            email.setText(studentModal.getStudentEmail());
            pass.setText(studentModal.getStudentPassword());
            phone.setText(studentModal.getStudentPhone());
            studentID = studentModal.getStudentId();
            Picasso.get().load(studentModal.getStudentImage()).fit().transform(new CircleTransform()).into(imgStud);

        }



        btn_seeRecord.setOnClickListener(v -> {
            Intent i = new Intent(StudentDashboard.this, StudentRecordListActivity.class);
            i.putExtra("EMAIL", studentModal.getStudentEmail());
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        });

        btn_seeProgress.setOnClickListener(v -> {
            Intent i = new Intent(StudentDashboard.this, Extra.class);
            i.putExtra("EMAIL", studentModal.getStudentEmail());
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        });
    }

    public void showAlertDialogButtonClicked(View view)
    {

        // Create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Change Password");
        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.dialog_pass, null);
        builder.setView(customLayout);
        // add a button
        builder.setPositiveButton("Save",
                        (dialog, which) -> {
                            // send data from the
                            // AlertDialog to the Activity
                            EditText editTextPass = customLayout.findViewById(R.id.editTextDialogPass);
                            sendDialogDataToActivity(editTextPass.getText().toString());
                        });
        // create and show
        // the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void showAlertDialogButtonClicked2(View view)
    {

        // Create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Change Phone");
        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.dialog_phone, null);
        builder.setView(customLayout);
        // add a button
        builder.setPositiveButton("Save",
                (dialog, which) -> {
                    // send data from the
                    // AlertDialog to the Activity
                    EditText editTextPhone = customLayout.findViewById(R.id.editTextDialogPhone);
                    sendDialogDataToActivity2(editTextPhone.getText().toString());
                });
        // create and show
        // the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Do something with the data
    // coming from the AlertDialog
    private void sendDialogDataToActivity(String dataPass)
    {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Students").child(studentID);
        Map<String, Object> map = new HashMap<>();
        map.put("studentPassword", dataPass);
        //Toast.makeText(this, data, Toast.LENGTH_SHORT).show();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // adding a map to our database.
                databaseReference.updateChildren(map);
                // on below line we are displaying a toast message.
                Toast.makeText(StudentDashboard.this, "Password Update Successful..Login Again", Toast.LENGTH_SHORT).show();
                // opening a new activity after updating our coarse.
                startActivity(new Intent(StudentDashboard.this, StudentAct.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // displaying a failure message on toast.
                Toast.makeText(StudentDashboard.this, "Failed to update..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Do something with the data
    // coming from the AlertDialog
    private void sendDialogDataToActivity2(String dataPhone)
    {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Students").child(studentID);
        Map<String, Object> map = new HashMap<>();
        map.put("studentPhone", dataPhone);
        //Toast.makeText(this, data, Toast.LENGTH_SHORT).show();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // adding a map to our database.
                databaseReference.updateChildren(map);
                // on below line we are displaying a toast message.
                Toast.makeText(StudentDashboard.this, "Phone Update Successful..", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // displaying a failure message on toast.
                Toast.makeText(StudentDashboard.this, "Failed to update..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}