package com.example.quizzapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizzapp.databinding.ActivityMain4Binding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity4 extends AppCompatActivity {

    android.widget.Button btns;
    TextView alrd;
    EditText name,phone,email,pass,cpass;
    FirebaseAuth auth;
    FirebaseFirestore database;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();
        btns = findViewById(R.id.sign);
        alrd = findViewById(R.id.ald);
        phone = findViewById(R.id.editTextPhone);
        name = findViewById(R.id.PersonName);
        email = findViewById(R.id.editTextTextEmailAddress);
        pass = findViewById(R.id.editTextTextPassword);
        cpass = findViewById(R.id.TextPassword);


        dialog = new ProgressDialog(this);
        database = FirebaseFirestore.getInstance();
        dialog.setMessage("Creating your account");

        if(auth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(),Category.class));
            finish();
        }

        alrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity4.this,MainActivity5.class);
                startActivity(i);
                finish();
            }
        });

        btns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Email = email.getText().toString().trim();
                String Pass = pass.getText().toString().trim();
                String Name = name.getText().toString();
                String Phone = phone.getText().toString();
                String Cpass = cpass.getText().toString();

                if(TextUtils.isEmpty(Email))
                {
                    email.setError("Field cannot be empty");
                    return;

                }
                if(TextUtils.isEmpty(Pass))
                {
                    pass.setError("Field cannot be empty");
                    return;

                }
                if(TextUtils.isEmpty(Name))
                {
                    name.setError("Field cannot be empty");
                    return;

                }
                if(TextUtils.isEmpty(Phone))
                {
                    phone.setError("Field cannot be empty");
                    return;

                }
                if(!Pass.equals(Cpass))
                {

                    cpass.setError("Password do not match");
                    return;
                }

                dialog.show();
                User user = new User(Name,Email,Pass);

                auth.createUserWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            String uid = task.getResult().getUser().getUid();
                            database.collection("users").document(uid).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {

                                        dialog.dismiss();
                                        Toast.makeText(MainActivity4.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(MainActivity4.this,Category.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        dialog.dismiss();
                                        Toast.makeText(MainActivity4.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                    }
                });

            }
        });

    }
}