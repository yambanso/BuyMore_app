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

public class userRegister extends AppCompatActivity {

    TextInputEditText Txtmail, Txtaddress,TxtPassword,conPassword;
    Button regBtn;
    TextView regText;
    ProgressBar progBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        Txtmail = findViewById(R.id.email);
        Txtaddress = findViewById(R.id.location);
        TxtPassword = findViewById(R.id.Userpassword);
        conPassword = findViewById(R.id.pwordCornfirm);
        regText = findViewById(R.id.regText);
        regBtn = findViewById(R.id.registerBtn);
        progBar = findViewById(R.id.progressBar);

        progBar.setVisibility(View.INVISIBLE);

        regBtn.setOnClickListener(v -> {
            String mail,address,password, ConPassword;
            mail = String.valueOf(Txtmail.getText());
            address = String.valueOf(Txtaddress.getText());
            password = String.valueOf(TxtPassword.getText());
            ConPassword = String.valueOf(conPassword.getText());

            // checking if the neccesary data is submitted
            if(mail.isEmpty() && address.isEmpty() && password.isEmpty() && ConPassword.isEmpty()){
                Toast.makeText(getApplicationContext(),"Form is incomplete", Toast.LENGTH_SHORT).show();
            }else{
                //checking if password is equal to cornfirm password
                if(!(ConPassword.equals(password))){
                    Toast.makeText(getApplicationContext(),"Please make sure that Password and Password Cornfirm are equal", Toast.LENGTH_SHORT).show();
                }else{

                    progBar.setVisibility(View.VISIBLE);
                    Intent intent = new Intent(getApplicationContext(),login.class);
                    startActivity(intent);
                    finish();

                }
            }

        });

        regText.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),login.class);
            startActivity(intent);
            finish();
        });
    }
    }
