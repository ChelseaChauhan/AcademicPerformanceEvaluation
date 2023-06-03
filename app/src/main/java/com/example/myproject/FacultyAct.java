package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class FacultyAct extends AppCompatActivity {
    Button btn2_signup,btn_signin;
    EditText user_name, pass_word,cnf_Pwd;
    FirebaseAuth mAuth;
    FloatingActionButton backHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facultyact);
        getSupportActionBar().setTitle("Sign Up");

        user_name = findViewById(R.id.Username);
        pass_word = findViewById(R.id.Password);
        cnf_Pwd = findViewById(R.id.cnfrmPassword);
        btn2_signup = findViewById(R.id.signup);
        //btn_signin=findViewById(R.id.listin);
        mAuth = FirebaseAuth.getInstance();

        backHome = findViewById(R.id.idbackHome);
        // on below line adding a click listener for our floating action button.
        backHome.setOnClickListener(v -> {
            // opening a new activity for adding a course.
            Intent i = new Intent(FacultyAct.this, MainActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });

        btn2_signup.setOnClickListener(v -> {
            register();
        });
    }


    private void register() {
            String email = user_name.getText().toString().trim();
            String password= pass_word.getText().toString().trim();
            String cnfPwd = cnf_Pwd.getText().toString();
            // checking if the password and confirm password is equal or not.
            if (!password.equals(cnfPwd)) {
                Toast.makeText(FacultyAct.this, "Please check both having same password..", Toast.LENGTH_SHORT).show();
            }
            else {
                if (email.isEmpty()) {
                    user_name.setError("Email is empty");
                    user_name.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    user_name.setError("Enter the valid email address");
                    user_name.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    pass_word.setError("Enter the password");
                    pass_word.requestFocus();
                    return;
                }
                if (password.length() < 6) {
                    pass_word.setError("Length of the password should be more than 6");
                    pass_word.requestFocus();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                       // Toast.makeText(FacultyAct.this, "Registration successful", Toast.LENGTH_SHORT).show();
                        sendmail();
                        showDialog();
                        user_name.setText("");
                        pass_word.setText("");
                        cnf_Pwd.setText("");


                    } else {
                       // Toast.makeText(FacultyAct.this, "Registration unsuccessful", Toast.LENGTH_SHORT).show();
                        errorMessage();
                    }
                });
            }

      /*  btn_signin.setOnClickListener(v -> {
            Intent intent = new Intent(FacultyAct.this,FacultyListActivity.class );
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }); */
    }

    public void showDialog() {
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE).setTitleText("Done!").setContentText("Registration Successful!").show();
    }
    public void errorMessage() {
        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText("Something went wrong! Try Again..").show();
    }
    private void sendmail() {
        String mEmail = user_name.getText().toString();
        String mPass = pass_word.getText().toString();
        String mSubject = "APES Login Credentials";
        String mMessage = "CONGRATULATIONS!! You are now successfully registered with APES." + "\n\n"
                + "EMAIL: " + mEmail + "\n" + "PASSWORD: " + mPass;

        JavaMailAPI javaMailAPI = new JavaMailAPI(this, mEmail, mSubject, mMessage);
        javaMailAPI.execute();

    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}

