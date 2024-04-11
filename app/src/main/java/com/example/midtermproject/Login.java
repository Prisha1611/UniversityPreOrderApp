package com.example.midtermproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Button loginButton, registerButton, CafeButton;
    EditText studentID, studentPassword;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        CafeButton = findViewById(R.id.CafeButton);
        studentID = findViewById(R.id.studentID);
        studentPassword = findViewById(R.id.studentPassword);
        db = new DBHelper(Login.this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), register.class);
                startActivity(intent);
            }
        });
        CafeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cafeside.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameStr = studentID.getText().toString();
                int username = Integer.parseInt(usernameStr);
                String password = studentPassword.getText().toString().trim();
                boolean isValidUser = db.checkUserCredentials(username, password);
                if (isValidUser) {
                    Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), HomePage.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
