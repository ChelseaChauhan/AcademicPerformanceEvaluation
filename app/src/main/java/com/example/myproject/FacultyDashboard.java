package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FacultyDashboard extends AppCompatActivity implements StudentRVadapter.StudentClickInterface {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private ArrayList<StudentModal> studentRVModalArrayList;
    private StudentRVadapter studentRVadapter;
    private RelativeLayout homeRL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facultydashboard);
        getSupportActionBar().setTitle("All Students");
        RecyclerView studentRV = findViewById(R.id.idRVStudent);
        homeRL = findViewById(R.id.idRLBSheet);
        FloatingActionButton backHome = findViewById(R.id.idbackHome);
        firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        studentRVModalArrayList = new ArrayList<>();
        // on below line we are getting database reference.
        databaseReference = firebaseDatabase.getReference("Students");


        // on below line adding a click listener for our floating action button.
        backHome.setOnClickListener(v -> {
            // opening a new activity for adding a course.
            Intent i = new Intent(FacultyDashboard.this, FacultyDashone.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });


        // on below line initializing our adapter class.
        studentRVadapter = new StudentRVadapter(studentRVModalArrayList, this, this::onStudentClick);
        // setting layout malinger to recycler view on below line.
        studentRV.setLayoutManager(new LinearLayoutManager(this));
        // setting adapter to recycler view on below line.
        studentRV.setAdapter(studentRVadapter);
        // on below line calling a method to fetch courses from database.
        getStudents();
    }
    private void getStudents() {
        // on below line clearing our list.
        studentRVModalArrayList.clear();
        // on below line we are calling add child event listener method to read the data.
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // adding snapshot to our array list on below line.
                studentRVModalArrayList.add(snapshot.getValue(StudentModal.class));
                // notifying our adapter that data has changed.
                studentRVadapter.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // this method is called when new child is added
                // we are notifying our adapter and making progress bar
                // visibility as gone.
                studentRVadapter.notifyDataSetChanged();
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                // notifying our adapter when child is removed.
                studentRVadapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // notifying our adapter when child is moved.
                studentRVadapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    @Override
    public void onStudentClick(int position) {
        // calling a method to display a bottom sheet on below line.
        displayBottomSheet(studentRVModalArrayList.get(position));
    }

    private void displayBottomSheet(StudentModal modal) {
        // on below line we are creating our bottom sheet dialog.
        final BottomSheetDialog bottomSheetTeachersDialog = new BottomSheetDialog(this, R.style.BottomSheetDialogTheme);
        // on below line we are inflating our layout file for our bottom sheet.
        View layout = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_layout, homeRL);
        // setting content view for bottom sheet on below line.
        bottomSheetTeachersDialog.setContentView(layout);
        // on below line we are setting a cancelable
        bottomSheetTeachersDialog.setCancelable(false);
        bottomSheetTeachersDialog.setCanceledOnTouchOutside(true);
        // calling a method to display our bottom sheet.
        bottomSheetTeachersDialog.show();
        // on below line we are creating variables for
        // our text view and image view inside bottom sheet
        // and initialing them with their ids.
        TextView studentName = layout.findViewById(R.id.idStudentName);
        TextView studentEmail = layout.findViewById(R.id.idStudentEmail);
        TextView studentPhone = layout.findViewById(R.id.idStudentPhone);
        TextView studentEnroll = layout.findViewById(R.id.idStudentEnroll);
        ImageView studentImg = layout.findViewById(R.id.idStudentImg);
        // on below line we are setting data to different views on below line.
        studentName.setText(modal.getStudentName());
        studentEnroll.setText(modal.getStudentEnroll());
        studentEmail.setText(modal.getStudentEmail());
        studentPhone.setText(modal.getStudentPhone());
        Picasso.get().load(modal.getStudentImage()).into(studentImg);
       // Button enterBtn = layout.findViewById(R.id.idBtnEnterStud);
        Button editBtn = layout.findViewById(R.id.idBtnEditStud);

        // adding on click listener for our edit button.
      /*  enterBtn.setOnClickListener(v -> {
            // on below line we are opening our EditCourseActivity on below line.
            Intent i = new Intent(FacultyDashboard.this, Marksheet.class);
            // on below line we are passing our course modal
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });*/

        editBtn.setOnClickListener(v -> {
            // on below line we are opening our EditCourseActivity on below line.
            Intent i = new Intent(FacultyDashboard.this, EditStudentActivity.class);
            // on below line we are passing our course modal
            i.putExtra("Student", modal);
            startActivity(i);
        });
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
