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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddDetailsActivity extends AppCompatActivity {

    Button signUp;
    EditText userName, userAge, userWeight, userDiet, userConditions;
    TextView textViewSignIn;

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);

        firebaseAuth = FirebaseAuth.getInstance();

        signUp = findViewById(R.id.signUpButton);

        userName = findViewById(R.id.signUpName);
        userAge = findViewById(R.id.signUpAge);
        userWeight = findViewById(R.id.signUpWeight);
        userDiet = findViewById(R.id.signUpDiet);
        userConditions = findViewById(R.id.signUpConditions);

        textViewSignIn = findViewById(R.id.textViewSignIn);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendUserData();
                Toast.makeText(AddDetailsActivity.this, "Registered Successfully",
                        Toast.LENGTH_LONG).show();
                startActivity(new Intent(AddDetailsActivity.this, ProfileActivity.class));
            }
        });

    }

    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("Users");
        String userId = firebaseAuth.getInstance().getCurrentUser().getUid();

        User user = new User(userName.getText().toString(),
                userAge.getText().toString(),
                userWeight.getText().toString(),
                userDiet.getText().toString(),
                userConditions.getText().toString());
        myRef.child(userId).setValue(user);

    }

    public void loginClick (View view){
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }

    public void skip (View view){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }







    public static class User{
        public String userName;
        public String userAge;
        public String userWeight;
        public String userDiet;
        public String userConditions;

        public User(){
        }

        public User(String userName, String userAge, String userWeight, String userDiet, String userConditions){
            this.userName = userName;
            this.userAge = userAge;
            this.userWeight = userWeight;
            this.userDiet = userDiet;
            this.userConditions = userConditions;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserAge() {
            return userAge;
        }

        public void setUserAge(String userAge) {
            this.userAge = userAge;
        }

        public String getUserWeight() {
            return userWeight;
        }

        public void setUserWeight(String userWeight) {
            this.userWeight = userWeight;
        }

        public String getUserDiet() {
            return userDiet;
        }

        public void setUserDiet(String userDiet) {
            this.userDiet = userDiet;
        }

        public String getUserConditions() {
            return userConditions;
        }

        public void setUserConditions(String userConditions) {
            this.userConditions = userConditions;
        }
    }
}



