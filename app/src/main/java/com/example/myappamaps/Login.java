package com.example.myappamaps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private EditText us, pw;
    private Button login;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DatabaseHelper(this); // Initialize the dbHelperv

        us = findViewById(R.id.txtus);
        pw = findViewById(R.id.txtpw);
        login = findViewById(R.id.log);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = us.getText().toString();
                String password = pw.getText().toString();

                if (authenticateUser(username, password)) {
                    // Authentication successful, determine user type and navigate accordingly
                    String userType = dbHelper.getUserType(username);

                    if ("Admin".equals(userType)) {
                        startActivity(new Intent(Login.this, admin.class));
                    } else if ("Customer".equals(userType)) {
                        startActivity(new Intent(Login.this,customer.class));
                    } else {
                        // Handle unknown user type
                        Toast.makeText(Login.this, "Unknown user type", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Authentication failed, show an error message
                    Toast.makeText(Login.this, "Authentication failed. Please check your credentials.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean authenticateUser(String username, String password) {
        // Implement authentication logic using your DatabaseHelper
        return dbHelper.authenticateUser(username, password);
    }


    public void sign(View v){
        Intent int1=new Intent(this,Register.class);
        startActivity(int1);
    }
}