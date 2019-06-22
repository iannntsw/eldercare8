package com.example.eldercare.HealthMonitor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.eldercare.DayPlanner.DayPlannerActivity;
import com.example.eldercare.DayPlanner.UpdateDayPlanActivity;
import com.example.eldercare.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class UpdateHealthActivity extends AppCompatActivity {

    private static final String TAG = "Update Day Activity" ;
    EditText dateHealth,systolicHealth,heartHealth,mapHealth,weightHealth;
    Button saveHealthBtn, deleteBtn;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_health);

        saveHealthBtn = findViewById(R.id.btnSaveUpdate);
        deleteBtn = findViewById(R.id.btnDelete);

        dateHealth = findViewById(R.id.dateHealth);
        systolicHealth = findViewById(R.id.systolicHealth);
        heartHealth = findViewById(R.id.heartHealth);
        mapHealth = findViewById(R.id.mapHealth);
        weightHealth = findViewById(R.id.weightHealth);

        dateHealth.setText(getIntent().getStringExtra("dateHealth"));
        systolicHealth.setText(getIntent().getStringExtra("systolicHealth"));
        heartHealth.setText(getIntent().getStringExtra("heartHealth"));
        mapHealth.setText(getIntent().getStringExtra("mapHealth"));
        weightHealth.setText(getIntent().getStringExtra("weightHealth"));

        final String keyHealthId = getIntent().getStringExtra("healthId");

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        String userId = firebaseAuth.getCurrentUser().getUid();
        databaseReference = firebaseDatabase.getReference("Users")
                .child(userId).child("Health").child("Health" + keyHealthId);
        Log.d(TAG, "onChanged: the query is exhausted..." + keyHealthId);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (keyHealthId != null){
                    Log.wtf(TAG,"onclick:delete card" + keyHealthId);
                    databaseReference.removeValue();
                }

                Intent intent = new Intent(UpdateHealthActivity.this, HealthActivity.class);
                startActivity(intent);
            }
        });

        //make an event for button
        saveHealthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Log.d(TAG,"update card" + keyHealthId);
                        dataSnapshot.getRef().child("dateHealth").setValue(dateHealth.getText().toString());
                        dataSnapshot.getRef().child("systolicHealth").setValue(systolicHealth.getText().toString());
                        dataSnapshot.getRef().child("heartHealth").setValue(heartHealth.getText().toString());
                        dataSnapshot.getRef().child("mapHealth").setValue(mapHealth.getText().toString());
                        dataSnapshot.getRef().child("weightHealth").setValue(weightHealth.getText().toString());
                        dataSnapshot.getRef().child("healthId").setValue(keyHealthId);

                        Intent intent = new Intent(UpdateHealthActivity.this, HealthActivity.class);
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
