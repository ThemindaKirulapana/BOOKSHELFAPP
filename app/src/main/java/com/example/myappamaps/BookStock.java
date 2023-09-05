package com.example.myappamaps;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BookStock extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    Bookadapter bookadapter;
    ArrayList<BookClass>list;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(BookStock.this, admin.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_stock);

        recyclerView=findViewById(R.id.Booklist);
        database= FirebaseDatabase.getInstance().getReference("bookstock");
      //  recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        list=new ArrayList<>();
        bookadapter=new Bookadapter(this,list);
        recyclerView.setAdapter(bookadapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot:snapshot.getChildren()){

                    BookClass bookClas=dataSnapshot.getValue(BookClass.class);
                    list.add(bookClas);
                }
                bookadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}