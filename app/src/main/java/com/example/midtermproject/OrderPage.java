package com.example.midtermproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderPage extends AppCompatActivity {
    private TextView dis;
    private Button plc, feedback, cancel, logout;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        dbHelper = new DBHelper(this); // Initialize DBHelper

        dis = findViewById(R.id.dis);
        plc = findViewById(R.id.plc);
        cancel = findViewById(R.id.cancel);
        feedback = findViewById(R.id.feedback);
        logout = findViewById(R.id.logout);

        // Retrieve passed username and other data
        int username = getIntent().getIntExtra("username", -1); // default to -1 if not found
        ArrayList<String> str = getIntent().getStringArrayListExtra("str");
        ArrayList<String> qn = getIntent().getStringArrayListExtra("qn");
        ArrayList<String> pr = getIntent().getStringArrayListExtra("pr");
        String totalAmount = getIntent().getStringExtra("tot");

        StringBuilder orderDetails = new StringBuilder();
        for (int i = 0; i < str.size(); i++) {
            orderDetails.append((i + 1) + ". " + str.get(i) + "-" + qn.get(i) + "-" + pr.get(i) + "\n");
        }
        dis.setText(orderDetails.toString() + "Total Amount     =     " + totalAmount);

        plc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if username is valid
                if (username != -1) {
                    boolean success = dbHelper.insertOrderDetails(username, orderDetails.toString(), totalAmount);
                    if (success) {
                        Toast.makeText(OrderPage.this, "Order Placed", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(OrderPage.this, "Failed to place order", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(OrderPage.this, "Error: User not identified", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrderPage.this, "Order Canceled", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OrderPage.this, HomePage.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderPage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Feedback.class);
                startActivity(intent);
            }
        });
    }
}
