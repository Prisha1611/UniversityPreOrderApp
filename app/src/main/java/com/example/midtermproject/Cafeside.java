package com.example.midtermproject;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class Cafeside extends AppCompatActivity {
    private TableLayout ordersTable;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafeside);

        ordersTable = findViewById(R.id.ordersTable);
        dbHelper = new DBHelper(this);

        displayOrders();
    }

    private void displayOrders() {
        List<Order> orderList = dbHelper.getAllOrders(); // Fetch all orders from the database

        for (Order order : orderList) {
            TableRow row = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);

            TextView tvOrderId = new TextView(this);
            tvOrderId.setText(String.valueOf(order.getOrderId()));
            styleTextView(tvOrderId);

            TextView tvUsername = new TextView(this);
            tvUsername.setText(String.valueOf(order.getUsername()));
            styleTextView(tvUsername);

            TextView tvOrderDetails = new TextView(this);
            tvOrderDetails.setText(order.getOrderDetails());
            styleTextView(tvOrderDetails);

            TextView tvTotalAmount = new TextView(this);
            tvTotalAmount.setText(order.getTotalAmount());
            styleTextView(tvTotalAmount);

            row.addView(tvOrderId);
            row.addView(tvUsername);
            row.addView(tvOrderDetails);
            row.addView(tvTotalAmount);

            ordersTable.addView(row);
        }
    }

    // Utility method to style TextViews
    private void styleTextView(TextView tv) {
        tv.setPadding(8, 8, 8, 8);
        tv.setTypeface(null, Typeface.BOLD);
        tv.setLayoutParams(new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
    }
}
