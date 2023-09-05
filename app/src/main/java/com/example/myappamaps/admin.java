package com.example.myappamaps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public  void register(View v){

        Intent int2=new Intent(this,BookADD.class);
        startActivity(int2);
    }
    public void veiew(View v){

        Intent int3=new Intent(this,BookStock.class);
        startActivity(int3);
    }
}