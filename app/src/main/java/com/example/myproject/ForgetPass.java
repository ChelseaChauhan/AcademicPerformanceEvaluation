package com.example.myproject;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPass extends AppCompatActivity {
    private EditText inputEmail;
    private Button btnReset;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpass);
        getSupportActionBar().setTitle("Reset Password");
        inputEmail = findViewById(R.id.email);
        btnReset = findViewById(R.id.reset_pass);
        mAuth = FirebaseAuth.getInstance();

        btnReset.setOnClickListener(v -> {

            String email = inputEmail.getText().toString().trim();
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
                return;
            }
            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgetPass.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                            inputEmail.setText("");
                        } else {
                            Toast.makeText(ForgetPass.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
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

