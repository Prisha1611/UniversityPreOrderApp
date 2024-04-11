package com.example.midtermproject;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String TABLE_USERDETAILS = "Userdetails";
    private static final String TABLE_ORDERS = "orders";
    private static final String TABLE_FEEDBACK = "feedback";
//    private static final String TABLE_COURSES = "Courses";

    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE Userdetails (username INTEGER PRIMARY KEY, password_hash TEXT, studentName TEXT)");
        DB.execSQL("CREATE TABLE " + TABLE_ORDERS + "(orderId INTEGER PRIMARY KEY AUTOINCREMENT, username INTEGER, orderDetails TEXT, totalAmount TEXT, FOREIGN KEY(username) REFERENCES Userdetails(username))");
        DB.execSQL("CREATE TABLE " + TABLE_FEEDBACK + " (id INTEGER PRIMARY KEY AUTOINCREMENT, feedbackText TEXT, timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)");
//        DB.execSQL("CREATE TABLE " + TABLE_COURSES + "(courseId INTEGER PRIMARY KEY AUTOINCREMENT, studentId INTEGER, courseName TEXT, UNIQUE(studentId, courseName))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("DROP TABLE IF EXISTS Userdetails");
        DB.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
        DB.execSQL("DROP TABLE IF EXISTS " + TABLE_FEEDBACK);
//        DB.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        onCreate(DB);
    }

    public boolean insertUserData(int username, String hashedPassword, String studentName) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password_hash", hashedPassword);
        contentValues.put("studentName", studentName);
        long result = DB.insert("Userdetails", null, contentValues);
        DB.close();
        return result != -1;
    }


    public boolean insertOrderDetails(int username, String orderDetails, String totalAmount) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);  // Include username in the ContentValues
        contentValues.put("orderDetails", orderDetails);
        contentValues.put("totalAmount", totalAmount);

        long result = DB.insert("orders", null, contentValues);
        DB.close();

        return result != -1;  // Return true if insertion is successful
    }


    public boolean insertFeedback(String feedbackText) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("feedbackText", feedbackText);

        try {
            long result = DB.insertOrThrow(TABLE_FEEDBACK, null, contentValues);
            return result != -1;
        } catch (Exception e) {
            Log.e("DBHelper", "Error inserting feedback: " + e.getMessage());
            return false;
        } finally {
            DB.close();
        }
    }


//    public boolean insertCourse(int studentId, String courseName) {
//        if (courseName == null || courseName.trim().isEmpty()) {
//            Log.e("DBHelper", "Course name is invalid");
//            return false;
//        }
//
//        SQLiteDatabase DB = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("studentId", studentId);
//        contentValues.put("courseName", courseName);
//
//        try {
//            long result = DB.insertOrThrow(TABLE_COURSES, null, contentValues);
//            return result != -1;
//        } catch (Exception e) {
//            Log.e("DBHelper", "Error inserting course: " + e.getMessage(), e);
//            return false;
//        } finally {
//            DB.close();
//        }
//    }


    // Method to retrieve all courses
//    public Cursor getAllCourses() {
//        SQLiteDatabase DB = this.getReadableDatabase();
//        return DB.rawQuery("SELECT * FROM " + TABLE_COURSES, null);
//    }
//    public boolean checkUserCredentials(int username, String password) {
//        SQLiteDatabase DB = this.getReadableDatabase();
//        Cursor cursor = DB.rawQuery("SELECT * FROM Userdetails WHERE username = ? AND password = ?",
//                new String[]{String.valueOf(username), password});
//        boolean exists = cursor.getCount() > 0;
//        cursor.close();
//        DB.close();
//        return exists;
//    }

    public boolean checkUserCredentials(int username, String password) {
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("SELECT password_hash FROM Userdetails WHERE username = ?", new String[]{String.valueOf(username)});
        if (cursor != null && cursor.moveToFirst()) {
            String storedHash = cursor.getString(0);
            cursor.close();
            DB.close();
            // Compare the hash of the input password with the stored hash
            return verifyPassword(password, storedHash);
        }
        return false;
    }

    private boolean verifyPassword(String plainTextPassword, String hashedPassword) {
        try {
            // Assuming bcrypt is used for hashing
            return BCrypt.checkpw(plainTextPassword, hashedPassword);
        } catch (Exception e) {
            Log.e("DBHelper", "Error verifying password: " + e.getMessage());
            return false;
        }
    }

    public List<Order> getAllOrders() {
        List<Order> orderList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT orderId, username, orderDetails, totalAmount FROM " + TABLE_ORDERS, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int orderId = cursor.getInt(cursor.getColumnIndexOrThrow("orderId"));
                int username = cursor.getInt(cursor.getColumnIndexOrThrow("username"));
                String orderDetails = cursor.getString(cursor.getColumnIndexOrThrow("orderDetails"));
                String totalAmount = cursor.getString(cursor.getColumnIndexOrThrow("totalAmount"));

                Order order = new Order(orderId, username, orderDetails, totalAmount);
                orderList.add(order);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return orderList;
    }

}