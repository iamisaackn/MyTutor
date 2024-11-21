package com.quizzes.mytutor;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private EditText name,email, pass, cpass;
    private Button sbutton;
    private ImageView backB;
    private FirebaseAuth mAuth;
    private String emailStr, passStr, confrimPassStr, nameStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);


        name = findViewById(R.id.user_name);
        email = findViewById(R.id.email_id);
        pass = findViewById(R.id.password_id);
        cpass = findViewById(R.id.cpassword);
        sbutton = findViewById(R.id.sign_button);
        backB = findViewById(R.id.back_icon);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        sbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (val()) {
                    signupNewUser();
                }
            }

        });
    }

    private boolean val(){
            nameStr = name.getText().toString().trim();
            emailStr = email.getText().toString().trim();
            passStr = pass.getText().toString().trim();
            confrimPassStr = cpass.getText().toString().trim();

            if(nameStr.isEmpty()){
                name.setError("Enter your name");
                return false;
            }
            if(emailStr.isEmpty()){
                email.setError("Enter your email");
                return false;
            }
            if(passStr.isEmpty()){
                pass.setError("Password should have 6 characters");
                return false;
            }
            if(confrimPassStr.isEmpty()){
                cpass.setError("Confirm your password");
                return false;
            }
            if(passStr.compareTo(confrimPassStr) != 0){
                Toast.makeText(SignUpActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        }
        private void signupNewUser(){
            mAuth.createUserWithEmailAndPassword(emailStr, passStr)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignUpActivity.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                startActivity(intent);
                                SignUpActivity.this.finish();
                            } else {
                                Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }


