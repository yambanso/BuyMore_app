package com.example.buymore_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buymore_app.backend.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class userRegister extends AppCompatActivity {
    private FirebaseAuth mAuth;

    TextInputEditText Txtmail, Txtaddress,TxtPhone,TxtPassword,conPassword;
    Button regBtn;
    TextView regText;
    ProgressBar progBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        mAuth = FirebaseAuth.getInstance();

        Txtmail = findViewById(R.id.email);//now phoneNumber
        Txtaddress = findViewById(R.id.location);
        TxtPassword = findViewById(R.id.Userpassword);
        TxtPhone = findViewById(R.id.phone);
        conPassword = findViewById(R.id.pwordCornfirm);
        regText = findViewById(R.id.regText);
        regBtn = findViewById(R.id.registerBtn);
        progBar = findViewById(R.id.progressBar);

        progBar.setVisibility(View.INVISIBLE);

        regBtn.setOnClickListener(v -> {
            String mail,contact,address,password, ConPassword;
            mail = String.valueOf(Txtmail.getText());
            contact = String.valueOf(TxtPhone.getText());
            address = String.valueOf(Txtaddress.getText());
            password = String.valueOf(TxtPassword.getText());
            ConPassword = String.valueOf(conPassword.getText());

            // checking if the neccesary data is submitted
            if(mail.isEmpty() ){
                Txtmail.setError("Email is required");
                Txtmail.requestFocus();
            }else if(contact.isEmpty()){
                TxtPhone.setError("Phone Number is required");
                Txtmail.requestFocus();
            }else if(address.isEmpty()){
                Txtaddress.setError("Home address is required");
                Txtaddress.requestFocus();
            }else if(password.isEmpty()){
                TxtPassword.setError("Password required");
                TxtPassword.requestFocus();
            }else if(ConPassword.isEmpty()){
                conPassword.setError("Password Confirm required");
                conPassword.requestFocus();

            }else {
                //checking if password is equal to cornfirm password
                if (!(ConPassword.equals(password))) {
                    Toast.makeText(getApplicationContext(), "Please make sure that Password and Password Cornfirm are equal", Toast.LENGTH_SHORT).show();
                }// checking validity of email address
                else if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                    Txtmail.setError("Invalid email address");
                    Txtmail.requestFocus();
                }// checking password length
                else if (password.length() < 6) {
                    TxtPassword.setError("min password lenght 6 charecters");
                    TxtPassword.requestFocus();
                } else {
                    progBar.setVisibility(View.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(mail,password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        User user = new User(mail,contact,address);
                                        FirebaseDatabase.getInstance().getReference("Users")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull @NotNull Task<Void> task) {
                                                if(task.isSuccessful()) {
                                                    progBar.setVisibility(View.GONE);
                                                    Toast.makeText(userRegister.this, "user Has been registered", Toast.LENGTH_LONG).show();

                                                    Intent intent = new Intent(getApplicationContext(), login.class);
                                                    startActivity(intent);
                                                    finish();
                                                }else{
                                                    Toast.makeText(userRegister.this, "user registration failled try again", Toast.LENGTH_LONG).show();
                                                    progBar.setVisibility(View.GONE);
                                                }
                                            }
                                        });
                                    }else{
                                        Toast.makeText(userRegister.this, "user registration failled try again", Toast.LENGTH_LONG).show();
                                        progBar.setVisibility(View.GONE);
                                    }
                                }
                            });

                    Toast.makeText(getApplicationContext(), "user Registered succesifully", Toast.LENGTH_SHORT).show();

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
