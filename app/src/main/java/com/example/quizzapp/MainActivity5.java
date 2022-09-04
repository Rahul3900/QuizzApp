package com.example.quizzapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity5 extends AppCompatActivity {
    TextView crt,frgt;
    EditText em,ps;
    FirebaseAuth auth;
    android.widget.Button btn;
    ProgressDialog dialog;
    ProgressDialog dg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        getSupportActionBar().hide();
         auth = FirebaseAuth.getInstance();
        crt = findViewById(R.id.create);
        btn = findViewById(R.id.log);
        em = findViewById(R.id.editTextTextEmailAddress);
        ps = findViewById(R.id.editTextTextPassword);
        frgt = findViewById(R.id.forgot);
        dialog = new ProgressDialog(this);
        dg = new ProgressDialog(this);
        dialog.setMessage("Logging in");
        dg.setMessage("Sending reset mail");

        crt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity5.this,MainActivity4.class);
                startActivity(i);
                finish();
            }
        });

        frgt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = em.getText().toString().trim();

                dg.show();
                if (TextUtils.isEmpty(email)) {
                    dg.dismiss();
                    Toast.makeText(getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
                    return;
                }


                auth.sendPasswordResetEmail(email)

                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    dg.dismiss();
                                    Toast.makeText(MainActivity5.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                                } else {
                                    dg.dismiss();
                                    Toast.makeText(MainActivity5.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email,pass;
                email = em.getText().toString();
                pass = ps.getText().toString();
                dialog.show();
                if(!email.isEmpty()&&!pass.isEmpty())
                {
                auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            dialog.dismiss();
                            Toast.makeText(MainActivity5.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity5.this, Category.class));
                        } else {
                            dialog.dismiss();
                            Toast.makeText(MainActivity5.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                }
            }
        });
    }
}