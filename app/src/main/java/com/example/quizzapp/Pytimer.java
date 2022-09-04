package com.example.quizzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Pytimer extends AppCompatActivity {

    android.widget.Button btn,mn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pytimer);
        getSupportActionBar().hide();

        btn = findViewById(R.id.tr);
        mn = findViewById(R.id.mn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Pytimer.this,Python.class);
                startActivity(i);
                finish();
            }
        });
        mn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Pytimer.this,Category.class);
                startActivity(i);
                finish();
            }
        });
    }
}