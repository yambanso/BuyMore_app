package com.example.buymore_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class login extends AppCompatActivity {

    TextInputEditText mail, password;
    Button login;
    TextView Acc;
    ProgressBar progbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mail = findViewById(R.id.mail);
        password = findViewById(R.id.Pword);
        Acc = findViewById(R.id.account);
        login = findViewById(R.id.login);
        progbar = findViewById(R.id.logprogressBar);

        progbar.setVisibility(View.INVISIBLE);

        Acc.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), userRegister.class);
            startActivity(intent);
            finish();
        });

        login.setOnClickListener(v -> {
            String email, pasword;
            email = String.valueOf(mail.getText());
            pasword = String.valueOf(password.getText());
            if(email.isEmpty() && pasword.isEmpty()){
                Toast.makeText(getApplicationContext(),"Form is incomplete", Toast.LENGTH_SHORT).show();
            }else{

                progbar.setVisibility(View.VISIBLE);

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();


            }
        });

    }
    }
