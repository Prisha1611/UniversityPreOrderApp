package com.example.midtermproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class register extends AppCompatActivity {
    EditText studentID, studentName, studentPassword;
    Button registerButton;

    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        studentID = findViewById(R.id.studentID);
        studentName = findViewById(R.id.studentName);
        studentPassword= findViewById(R.id.studentPassword);
        registerButton = findViewById(R.id.registerButton);

        db = new DBHelper(register.this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameStr = studentID.getText().toString();
                int username = Integer.parseInt(usernameStr);
                String password = studentPassword.getText().toString().trim();

                // Initialize DBHelper object
                db = new DBHelper(register.this);

                boolean isInserted = db.insertUserData(username, password);
                if (isInserted) {
                    Toast.makeText(register.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                    // Optionally, navigate to login screen
                } else {
                    Toast.makeText(register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}