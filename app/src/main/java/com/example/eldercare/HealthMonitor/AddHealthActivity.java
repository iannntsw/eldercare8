package com.example.eldercare.HealthMonitor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.eldercare.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class AddHealthActivity extends AppCompatActivity {

    Integer healthNum = new Random().nextInt();
    String healthId = Integer.toString(healthNum);
    EditText dateHealth,systolicHealth,heartHealth,mapHealth,weightHealth;
    Button addHealthBtn, cancelBtn;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_health);

        addHealthBtn = findViewById(R.id.btnSaveUpdate);
        cancelBtn = findViewById(R.id.btnDelete);

        dateHealth = findViewById(R.id.dateHealth);
        systolicHealth = findViewById(R.id.systolicHealth);
        heartHealth = findViewById(R.id.heartHealth);
        mapHealth = findViewById(R.id.mapHealth);
        weightHealth = findViewById(R.id.weightHealth);

        addHealthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth = FirebaseAuth.getInstance();
                firebaseDatabase = FirebaseDatabase.getInstance();
                String userId = firebaseAuth.getCurrentUser().getUid();
                DatabaseReference databaseReference = firebaseDatabase.getReference("Users")
                        .child(userId).child("Health").child("Health" + healthNum);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("dateHealth").setValue(dateHealth.getText().toString());
                        dataSnapshot.getRef().child("systolicHealth").setValue(systolicHealth.getText().toString());
                        dataSnapshot.getRef().child("heartHealth").setValue(heartHealth.getText().toString());
                        dataSnapshot.getRef().child("mapHealth").setValue(mapHealth.getText().toString());
                        dataSnapshot.getRef().child("weightHealth").setValue(weightHealth.getText().toString());
                        dataSnapshot.getRef().child("healthId").setValue(healthId);

                        Intent intent = new Intent(AddHealthActivity.this, HealthActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
