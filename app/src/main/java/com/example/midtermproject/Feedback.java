package com.example.midtermproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Feedback extends AppCompatActivity {
    private EditText feedbackInput;
    private Button submitFeedbackButton;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback); // Make sure this matches your XML file name

        // Initialize the DBHelper
        dbHelper = new DBHelper(this);

        // Find the EditText and Button by their IDs
        feedbackInput = findViewById(R.id.feedbackInput);
        submitFeedbackButton = findViewById(R.id.submitFeedbackButton);

        // Set an OnClickListener on the submit button
        submitFeedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitFeedback();
            }
        });
    }

    private void submitFeedback() {
        // Get the feedback text from the EditText
        String feedbackText = feedbackInput.getText().toString().trim();

        // Check if the feedback text is empty
        if (feedbackText.isEmpty()) {
            Toast.makeText(this, "Please enter some feedback before submitting.", Toast.LENGTH_LONG).show();
            return;
        }

        // Use DBHelper to insert the feedback into the database
        boolean isSuccess = dbHelper.insertFeedback(feedbackText);

        // Show a toast message based on whether the insert was successful
        if (isSuccess) {
            Toast.makeText(this, "Feedback submitted successfully", Toast.LENGTH_SHORT).show();
            feedbackInput.setText(""); // Clear the EditText after submission
            Intent intent = new Intent(Feedback.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Failed to submit feedback", Toast.LENGTH_SHORT).show();
        }
    }
}
