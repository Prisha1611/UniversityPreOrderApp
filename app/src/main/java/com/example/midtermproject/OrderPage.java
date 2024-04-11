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
    private DBHelper dbHelper; // Add this line

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

        ArrayList<String> str = getIntent().getStringArrayListExtra("str");
        ArrayList<String> qn = getIntent().getStringArrayListExtra("qn");
        ArrayList<String> pr = getIntent().getStringArrayListExtra("pr");

        StringBuilder orderDetails = new StringBuilder();
        for (int i = 0; i < str.size(); i++) {
            orderDetails.append((i + 1) + ". " + str.get(i) + "-" + qn.get(i) + "-" + pr.get(i) + "\n");
        }
        String totalAmount = getIntent().getStringExtra("tot");
        dis.setText(orderDetails.toString() + "Total Amount     =     " + totalAmount);

        plc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean success = dbHelper.insertOrderDetails(orderDetails.toString(), totalAmount);
                if(success) {
                    Toast.makeText(OrderPage.this, "Order Placed", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(OrderPage.this, "Failed to place order", Toast.LENGTH_SHORT).show();
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


//public class OrderPage extends AppCompatActivity {
//    private TextView dis;
//    private Button plc, feedback;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_order_page);
//        dis = (TextView)findViewById(R.id.dis);
//        feedback = findViewById(R.id.feedback);
//        String total ="";
//
//        ArrayList<String> str = getIntent().getStringArrayListExtra("str");
//        ArrayList<String> qn = getIntent().getStringArrayListExtra("qn");
//        ArrayList<String> pr = getIntent().getStringArrayListExtra("pr");
//
//        for (int i = 0; i < str.size() ; i++) {
//
//            total+=((i+1)+". ")+(str.get(i))+"-"+(qn.get(i))+"-"+(pr.get(i));
//            total+="\n";
//        }
//        total+="Total Amount     =     "+(getIntent().getStringExtra("tot").toString());
//        dis.setText(total);
//        plc = (Button) findViewById(R.id.plc);
//
//        plc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(OrderPage.this, "Order Placed", Toast.LENGTH_SHORT).show();
//            }
//        });
//        feedback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Feedback.class);
//                startActivity(intent);            }
//        });
//
//    }
//}

//public class OrderPage extends AppCompatActivity {
//    private TextView dis;
//    private Button plc;
//    private DBHelper dbHelper; // Add a DBHelper instance variable
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_order_page);
//
//        dis = findViewById(R.id.dis);
//        plc = findViewById(R.id.plc);
//
//        dbHelper = new DBHelper(this); // Initialize DBHelper
//
//        ArrayList<String> str = getIntent().getStringArrayListExtra("str");
//        ArrayList<String> qn = getIntent().getStringArrayListExtra("qn");
//        ArrayList<String> pr = getIntent().getStringArrayListExtra("pr");
//        int username = getIntent().getIntExtra("username", -1); // Ensure you pass "username" from previous Activity
//        Log.d("OrderPage", "Received username: " + username);
//        StringBuilder orderDetailsBuilder = new StringBuilder();
//
//        for (int i = 0; i < str.size(); i++) {
//            orderDetailsBuilder.append((i+1) + ". " + str.get(i) + "-" + qn.get(i) + "-" + pr.get(i) + "\n");
//        }
//        String totalAmount = getIntent().getStringExtra("tot");
//        String orderDetails = orderDetailsBuilder.toString();
//
//        dis.setText(orderDetails + "Total Amount     =     " + totalAmount);
//
//        plc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (username != -1) {
//                    // Assume totalAmount and orderDetails are correctly formatted and include all necessary info
//                    boolean result = dbHelper.insertOrderDetails(username, orderDetails, totalAmount);
//                    if (result) {
//                        Toast.makeText(OrderPage.this, "Order Placed", Toast.LENGTH_SHORT).show();
//                        // Optionally, navigate the user to a new Activity showing order confirmation or back to the main menu
//                    } else {
//                        Toast.makeText(OrderPage.this, "Failed to place order", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(OrderPage.this, "Error: User not identified", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//}



