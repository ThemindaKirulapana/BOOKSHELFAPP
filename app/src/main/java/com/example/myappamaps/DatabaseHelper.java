package com.example.myappamaps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {



    private static final String DATABASE_NAME = "BookShelf";
    private static final int DATABASE_VERSION = 1;

    private static final String USERS = "users";
    private static final String ID = "id";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String USER_TYPE = "user_type";

    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + USERS + "(" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            USERNAME + " TEXT, " +
            PASSWORD + " TEXT, " +
            USER_TYPE + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle schema changes
    }

    public boolean addUser(String username, String password, String userType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME, username);
        values.put(PASSWORD, password);
        values.put(USER_TYPE, userType);

        long result = db.insert(USERS, null, values); // Insert and get the result
        db.close();

        return result != -1; // If result is not -1, registration is successful
    }

    public boolean authenticateUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                USERS, // Table name
                new String[]{USERNAME, PASSWORD}, // Columns to return
                USERNAME + "=? AND " + PASSWORD + "=?", // WHERE clause
                new String[]{username, password}, // Values for the WHERE clause
                null, null, null
        );

        int count = cursor.getCount();
        cursor.close();
        db.close();

        return count > 0; // If count > 0, user is authenticated
    }

    public String getUserType(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String userType = null;

        // Query the database to get the user type based on the provided username
        Cursor cursor = db.query(
                USERS, // Table name
                new String[]{USER_TYPE}, // Columns to return
                USERNAME + "=?", // WHERE clause
                new String[]{username}, // Values for the WHERE clause
                null, null, null
        );

        if (cursor != null && cursor.moveToFirst()) {
            userType = cursor.getString(cursor.getColumnIndex(USER_TYPE));
            cursor.close();
        }

        db.close();
        return userType;
    }



}
