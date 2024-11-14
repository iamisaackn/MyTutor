package com.quizzes.mytutor;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    private EditText name, email, pass, confirmPass;
    private Button signUpB;
    private ImageView backB;
    private FirebaseAuth mAuth;
    private String emailStr, passStr, confirmPassStr, nameStr;
    private Dialog progressDialog;
    private TextView dialogText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize views
        name = findViewById(R.id.username);
        email = findViewById(R.id.emailID);
        pass = findViewById(R.id.password);
        confirmPass = findViewById(R.id.confirm_pass);
        signUpB = findViewById(R.id.signupB);
        backB = findViewById(R.id.backB);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize progress dialog
        progressDialog = new Dialog(SignUpActivity.this);
        progressDialog.setContentView(R.layout.dialog_layout);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogText = progressDialog.findViewById(R.id.dialog_text);
        dialogText.setText("Registering user...");

        // Set up listeners
        backB.setOnClickListener(view -> finish());

        signUpB.setOnClickListener(view -> {
            if (validate()) {
                signupNewUser();
            }
        });
    }

    private boolean validate() {
        // Validation logic here
        nameStr = name.getText().toString().trim();
        emailStr = email.getText().toString().trim();
        passStr = pass.getText().toString().trim();
        confirmPassStr = confirmPass.getText().toString().trim();

        if (nameStr.isEmpty()) {
            name.setError("Enter Your Name");
            return false;
        }

        if (emailStr.isEmpty()) {
            email.setError("Enter Email ID");
            return false;
        }

        if (passStr.isEmpty()) {
            pass.setError("Enter Password");
            return false;
        }

        if (confirmPassStr.isEmpty()) {
            confirmPass.setError("Enter Password");
            return false;
        }

        if (!passStr.equals(confirmPassStr)) {
            Toast.makeText(SignUpActivity.this, "Password and Confirm Password should be the same!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void signupNewUser() {
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(emailStr, passStr)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignUpActivity.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();

                        DbQuery.createUserData(emailStr, nameStr, new MyCompleteListener() {
                            @Override
                            public void onSuccess() {
                                progressDialog.dismiss();
                                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                startActivity(intent);
                                SignUpActivity.this.finish();
                            }

                            @Override
                            public void onFailure() {
                                Toast.makeText(SignUpActivity.this, "Something went wrong, please try again later!", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        });

                    } else {
                        // If sign up fails, display a message to the user.
                        progressDialog.dismiss();
                        Toast.makeText(SignUpActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}