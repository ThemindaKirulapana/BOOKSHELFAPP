package com.example.myappamaps;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils; // Import this for input validation
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast; // Import this for displaying messages

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class BookADD extends AppCompatActivity {

    private EditText bookIdEditText;
    private EditText nameEditText;
    private EditText priceEditText;
    private EditText authorEditText;
    private Spinner categoryEditText;
    private Spinner branchEditText;
    private Button insertButton,update;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_add);

        FirebaseApp.initializeApp(this);

        databaseReference = FirebaseDatabase.getInstance().getReference("bookstock");

        // Initialize UI elements
        bookIdEditText = findViewById(R.id.txtEditBID);
        nameEditText = findViewById(R.id.txtEditBname);
        priceEditText = findViewById(R.id.txtEditPrice);
        authorEditText = findViewById(R.id.txtEditAuthor);
        categoryEditText = findViewById(R.id.category);
        branchEditText = findViewById(R.id.txtbranch);
        insertButton = findViewById(R.id.btnAdd);
        update=findViewById(R.id.btnUpdate);

        List<String> categoryDataList = new ArrayList<>();
        categoryDataList.add("Fiction");
        categoryDataList.add("Classic");
        categoryDataList.add("Kids");
        categoryDataList.add("Novels");

        List<String> branchDataList = new ArrayList<>();
        branchDataList.add("Maharagama");
        branchDataList.add("Kiribathgoda");
        branchDataList.add("Ragama");
        branchDataList.add("Dehiwala");

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryDataList);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryEditText.setAdapter(categoryAdapter);

        ArrayAdapter<String> branchAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, branchDataList);
        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branchEditText.setAdapter(branchAdapter);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get input values from EditText fields
                String bookId = bookIdEditText.getText().toString();
                String name = nameEditText.getText().toString();
                String priceText = priceEditText.getText().toString();
                String author = authorEditText.getText().toString();
                String category = categoryEditText.getSelectedItem().toString(); // Get selected category
                String branch = branchEditText.getSelectedItem().toString();     // Get selected branch

                // Check for empty fields
                if (TextUtils.isEmpty(bookId) || TextUtils.isEmpty(name) || TextUtils.isEmpty(priceText) || TextUtils.isEmpty(author)) {
                    // Handle empty fields
                    Toast.makeText(BookADD.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    double price = Double.parseDouble(priceText);

                    // Create a BookClass object
                    BookClass bookStock = new BookClass(bookId, name, price, author, category, branch);
                    databaseReference.child(bookId).setValue(bookStock);

                    // Clear input fields
                    bookIdEditText.setText("");
                    nameEditText.setText("");
                    priceEditText.setText("");
                    authorEditText.setText("");

                    // Display a success message
                    Toast.makeText(BookADD.this, "Book added successfully", Toast.LENGTH_SHORT).show();
                } catch (NumberFormatException e) {
                    // Handle invalid price input
                    Toast.makeText(BookADD.this, "Invalid price format", Toast.LENGTH_SHORT).show();
                }
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intte=new Intent(BookADD.this,BookStock.class);
                startActivity(intte);
                finish();
            }
        });


    }
}
