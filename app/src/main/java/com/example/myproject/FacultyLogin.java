package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FacultyLogin extends AppCompatActivity {
    private EditText user_name, pass_word;
    Button btn_forgetpass, btn_login;
    FirebaseAuth mAuth;
    FloatingActionButton backHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facultylogin);
        getSupportActionBar().setTitle("Sign In");
        mAuth = FirebaseAuth.getInstance();

        backHome = findViewById(R.id.idbackHome);
        // on below line adding a click listener for our floating action button.
        backHome.setOnClickListener(v -> {
            // opening a new activity for adding a course.
            Intent i = new Intent(FacultyLogin.this, MainActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });



        user_name = findViewById(R.id.email);
        pass_word = findViewById(R.id.password);
        btn_login = findViewById(R.id.login);
        btn_forgetpass = findViewById(R.id.forgetpass);
        btn_login.setOnClickListener(v -> {
            String email = user_name.getText().toString().trim();
            String password = pass_word.getText().toString().trim();
            if (email.isEmpty()) {
                user_name.setError("Email is empty");
                user_name.requestFocus();
                return;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                user_name.setError("Enter the valid email");
                user_name.requestFocus();
                return;
            }
            if (password.isEmpty()) {
                pass_word.setError("Password is empty");
                pass_word.requestFocus();
                return;
            }
            if (password.length() < 6) {
                pass_word.setError("Length of password is more than 6");
                pass_word.requestFocus();
                return;
            }
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    startActivity(new Intent(FacultyLogin.this, FacultyDashone.class));
                    Toast.makeText(FacultyLogin.this, "Logging In..", Toast.LENGTH_SHORT).show();
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                } else {
                    Toast.makeText(FacultyLogin.this, "Please Check Your login Credentials", Toast.LENGTH_SHORT).show();
                }
            });
        });
        btn_forgetpass.setOnClickListener(v -> {
            Intent intent = new Intent(FacultyLogin.this,ForgetPass.class );
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
    }

   /* @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user!=null) {
                startActivity(new Intent(FacultyLogin.this, FacultyDashone.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    } */

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
