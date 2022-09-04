package com.example.quizzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Cptimer extends AppCompatActivity {

    android.widget.Button btnn,mn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cptimer);
        getSupportActionBar().hide();

        btnn = findViewById(R.id.tryy);
        mn = findViewById(R.id.mn);

        btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Cptimer.this,Ccp.class);
                startActivity(i);
                finish();
            }
        });
        mn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Cptimer.this,Category.class);
                startActivity(i);
                finish();
            }
        });
    }
}