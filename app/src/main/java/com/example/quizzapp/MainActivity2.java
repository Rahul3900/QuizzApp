package com.example.quizzapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

  LinearProgressIndicator progress;
  TextView quess,op1,op2,op3,op4,time,quesno,exit;
  ImageView imgv;
  CountDownTimer cd;
    int timer = 10;
    ArrayList<question> ques;
    int index = 0;
    question que;
    android.widget.Button next;
    FirebaseFirestore database;
    int correct =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
         getSupportActionBar().hide();
        progress = findViewById(R.id.timer);
        quess = findViewById(R.id.ques);
        op1 = findViewById(R.id.op1);
        op2 = findViewById(R.id.op2);
        op3 = findViewById(R.id.op3);
        op4 = findViewById(R.id.op4);
        time = findViewById(R.id.time);
        next = findViewById(R.id.next);
        quesno = findViewById(R.id.quesno);

        exit = findViewById(R.id.exit);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cd.cancel();
                Intent i = new Intent(getApplicationContext(),Category.class);
                startActivity(i);
                finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index==ques.size()-2){
                    next.setText("Finish");
                }

                if(index<ques.size()-1){

                cd.cancel();
               timer=10;
               cd.start();
                index++;
                reset();
                nextques();}else{
                    cd.cancel();
                    results();


                }
            }
        });

        cd=new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long l) {
                timer=timer-1;
                time.setText(String.format("%d",timer+1));
                progress.setProgress(timer);
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                startActivity(intent);
                finish();
            }
        }.start();


        ques = new ArrayList<>();
        database = FirebaseFirestore.getInstance();

        database.collection("Categories").document("Java").collection("questions").orderBy("index")
                        .limit(11).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                                for(DocumentSnapshot snapshot : queryDocumentSnapshots){
                                                    question quess = snapshot.toObject(question.class);
                                                    ques.add(quess);
                                                }
                                            nextques();
                                        }
                                    });



    }

    void results(){
        Intent i = new Intent(getApplicationContext(),Result.class);
        i.putExtra("correct",correct);
        startActivity(i);
    }
    void nextques(){
        if(index<ques.size()){
            quesno.setText(String.format("%d/%d",(index+1),ques.size()));
           que= ques.get(index);

            quess.setText(que.getQues());
            op1.setText(que.getAns1());
            op2.setText(que.getAns2());
            op3.setText(que.getAns3());
            op4.setText(que.getAns4());
        }
    }

    void answer(TextView textView){
        String selected = textView.getText().toString();
        if(selected.equals(que.getAcorrect())){
            correct++;
            textView.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        }else{
            showAns();
            textView.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
        }
    }

    void showAns(){
        if(que.getAcorrect().equals(op1.getText().toString()))
            op1.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        if(que.getAcorrect().equals(op2.getText().toString()))
            op2.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        if(que.getAcorrect().equals(op3.getText().toString()))
            op3.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        if(que.getAcorrect().equals(op4.getText().toString()))
            op4.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));

    }

    void reset(){
        op1.setBackground(getResources().getDrawable(R.drawable.op));
        op2.setBackground(getResources().getDrawable(R.drawable.op));
        op3.setBackground(getResources().getDrawable(R.drawable.op));
        op4.setBackground(getResources().getDrawable(R.drawable.op));
    }


    public void onClick(View view){
        switch (view.getId()){

            case R.id.op1:
            case R.id.op2:
            case R.id.op3:
            case R.id.op4:
                TextView selectedd = (TextView) view;
                answer(selectedd);
                break;


        }
    }

}