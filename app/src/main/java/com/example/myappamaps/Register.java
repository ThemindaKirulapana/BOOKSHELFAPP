package com.example.myappamaps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Spinner userTypeSpinner;
    private Button registerButton;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DatabaseHelper(this);

        usernameEditText = findViewById(R.id.txtus1);
        passwordEditText = findViewById(R.id.txtpw1);
        userTypeSpinner = findViewById(R.id.usertype1);
        registerButton = findViewById(R.id.sing);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String userType = userTypeSpinner.getSelectedItem().toString();

                boolean registrationSuccessful = dbHelper.addUser(username, password, userType);

                if (registrationSuccessful) {
                    // Registration successful, show a success message or navigate somewhere
                    Toast.makeText(Register.this, "Registration successful", Toast.LENGTH_SHORT).show();

                    // You can also navigate the user to the login page or another appropriate page
                } else {
                    // Registration failed, show an error message
                    Toast.makeText(Register.this, "Registration failed. Please try again", Toast.LENGTH_SHORT).show();

                }
                startActivity(new Intent(Register.this, Login.class));
            }
        });

    }


}