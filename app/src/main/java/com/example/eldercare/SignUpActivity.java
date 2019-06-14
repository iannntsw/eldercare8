package com.example.eldercare;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    Button signUp;
    EditText userEmail, userPassword, userName, userAge, userWeight, userDiet, userConditions;
    TextView textViewSignIn;
    String email, name, age, password, weight, diet, conditions;

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();

        signUp = findViewById(R.id.signUpButton);

        userEmail = findViewById(R.id.loginEmail);
        userPassword = findViewById(R.id.loginPassword);
        userName = findViewById(R.id.signUpName);
        userAge = findViewById(R.id.signUpAge);
        userWeight = findViewById(R.id.signUpWeight);
        userDiet = findViewById(R.id.signUpDiet);
        userConditions = findViewById(R.id.signUpConditions);

        textViewSignIn = findViewById(R.id.textViewSignIn);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    firebaseAuth.createUserWithEmailAndPassword(userEmail.getText().toString().trim(),
                            userPassword.getText().toString().trim())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SignUpActivity.this, "Registered Successfully",
                                                Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(SignUpActivity.this, LoginPage.class));
                                    } else {
                                        Toast.makeText(SignUpActivity.this, task.getException().getMessage(),
                                                Toast.LENGTH_LONG).show();

                                    }
                                }
                            });
            }
        });
    }


    public void loginClick (View view){
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }
}



