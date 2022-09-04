package com.example.quizzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class Category extends AppCompatActivity {

    ImageView java,c,ccp,ph;
    android.widget.Button out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportActionBar().hide();
        java = findViewById(R.id.java);
        c = findViewById(R.id.c);
        ccp = findViewById(R.id.ccp);
        ph = findViewById(R.id.python);
        out = findViewById(R.id.sout);

        java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Category.this,MainActivity2.class);
                startActivity(i);
                finish();
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Category.this,C.class);
                startActivity(i);
                finish();
            }
        });
        ccp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Category.this,Ccp.class);
                startActivity(i);
                finish();
            }
        });
        ph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Category.this,Python.class);
                startActivity(i);
                finish();
            }
        });

        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(Category.this,MainActivity5.class);
                startActivity(i);
                finish();
            }
        });
    }
}