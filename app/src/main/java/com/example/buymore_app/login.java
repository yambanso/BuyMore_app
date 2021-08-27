package com.example.buymore_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class login extends AppCompatActivity {

    private FirebaseAuth mAuth;

    TextInputEditText mail, password,emailReset;
    Button login,passwordReset;
    TextView Acc,reset;
    ProgressBar progbar;

    private AlertDialog.Builder dialogBuilder;
    private  AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        mail = findViewById(R.id.mail);
        password = findViewById(R.id.Pword);
        Acc = findViewById(R.id.account);
        reset = findViewById(R.id.passreset);
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
            email = String.valueOf(mail.getText()).trim();
            pasword = String.valueOf(password.getText());
            if(email.isEmpty()){
                mail.setError("please enter youre email address");
                mail.requestFocus();
            }else if(pasword.isEmpty()){
                password.setError("please enter password");
                password.requestFocus();
            }else{
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    mail.setError("please enter a valid email address");
                    mail.requestFocus();
                }else{
                    progbar.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(email,pasword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                progbar.setVisibility(View.INVISIBLE);
                                Toast.makeText(getApplicationContext(),"failled to sign in check credentials",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

                        }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createResetDialog();
            }
        });

    }

    public void createResetDialog(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View popup = getLayoutInflater().inflate(R.layout.pass_reset, null);
        emailReset = popup.findViewById(R.id.passmail);
        passwordReset = popup.findViewById(R.id.pasreset);
        Button cancel = popup.findViewById(R.id.cancel);
        ProgressBar prog = popup.findViewById(R.id.progressBar);
        dialogBuilder.setView(popup);
        dialog = dialogBuilder.create();
        dialog.show();
        prog.setVisibility(View.INVISIBLE);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        passwordReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String email = String.valueOf(emailReset.getText()).trim();
                   if(email.isEmpty()){
                       emailReset.setError("please enter youre email address");
                       emailReset.requestFocus();
                   }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                       emailReset.setError("please enter a valid email address");
                       emailReset.requestFocus();
                   }else{
                       prog.setVisibility(View.VISIBLE);
                       mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                           @Override
                           public void onComplete(@NonNull @NotNull Task<Void> task) {
                               if(task.isSuccessful()){
                                   dialog.dismiss();
                                   Toast.makeText(getApplicationContext(),"Password reset link sent sucessifuly to Email",Toast.LENGTH_LONG).show();

                            }else{
                                   Toast.makeText(getApplicationContext(),"failled to send to email reset link try again",Toast.LENGTH_LONG).show();

                               }
                           }
                       });

                   }
            }
        });
    }
    }
