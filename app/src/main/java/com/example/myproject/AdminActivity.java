package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {

    EditText AdminUserid, AdminPass;
    Button cont;
    String username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        getSupportActionBar().setTitle("Admin LogIn");
        AdminUserid = findViewById(R.id.AdminUserid);
        AdminPass = findViewById(R.id.AdminPass);
        cont = findViewById(R.id.cont);

        cont.setOnClickListener(v -> {
            username = AdminUserid.getText().toString().trim();
            password = AdminPass.getText().toString().trim();
            if(username.isEmpty() || password.isEmpty()) {
                Toast.makeText(AdminActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
            else {
                Validate(username,password);
            }
        });
    }
    private void Validate(String username,String userPass)
    {if((username.equals("admin"))&&(userPass.equals("admin@123")))
    {
        Intent intent=new Intent(AdminActivity.this,FacultyAct.class);
        Toast.makeText(AdminActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
        AdminUserid.setText("");
        AdminPass.setText("");
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    else{
        Toast.makeText(AdminActivity.this, "Please Check Your login Credentials", Toast.LENGTH_SHORT).show();
    }
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}