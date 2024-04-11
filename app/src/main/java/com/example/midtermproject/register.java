package com.example.midtermproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.mindrot.jbcrypt.BCrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class register extends AppCompatActivity {
    EditText studentID, studentName, studentPassword;  // Keep original names
    Button registerButton;

    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // References to EditTexts
        studentID = findViewById(R.id.studentID);
        studentName = findViewById(R.id.studentName);
        studentPassword = findViewById(R.id.studentPassword);
        registerButton = findViewById(R.id.registerButton);

        db = new DBHelper(register.this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Directly retrieve and trim the text from the EditTexts when needed
                String usernameStr = studentID.getText().toString().trim();
                int username = Integer.parseInt(usernameStr); // Assuming the username is intended to be an integer
                String password = studentPassword.getText().toString().trim();

                // Hash the password
                String hashedPassword = hashPassword(password);

                // Insert data with hashed password and directly retrieve student name here
                boolean isInserted = db.insertUserData(username, hashedPassword, studentName.getText().toString().trim());
                if (isInserted) {
                    Toast.makeText(register.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    // Hashing password using SHA-256
//    private String hashPassword(String password) {
//        try {
//            MessageDigest md = MessageDigest.getInstance("SHA-256");
//            byte[] hashedBytes = md.digest(password.getBytes());
//            return bytesToHex(hashedBytes);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    // Helper method to convert byte array into a hexadecimal string
    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    // Helper method to hash a password using bcrypt
    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

}
