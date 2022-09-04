package com.example.quizzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class Result extends AppCompatActivity {
    CircularProgressBar cb;
    TextView res;
    android.widget.Button play,main;
    int correct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().hide();
        cb = findViewById(R.id.circularProgressBar);
        res = findViewById(R.id.score);
        play = findViewById(R.id.playag);
        main = findViewById(R.id.mm);

        correct = getIntent().getIntExtra("correct",0);
        res.setText(String.format("%d/%d",correct,10));
        cb.setProgress(correct);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correct=0;
                Intent i = new Intent(Result.this,getApplicationContext().getClass());
                startActivity(i);
                finish();
            }
        });

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correct=0;
                Intent i = new Intent(Result.this,Category.class);
                startActivity(i);
                finish();
            }
        });

    }
}