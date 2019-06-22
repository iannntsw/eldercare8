package com.example.eldercare.HealthMonitor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.eldercare.DayPlanner.AddDayPlanActivity;
import com.example.eldercare.DayPlanner.DayPlannerActivity;
import com.example.eldercare.DayPlanner.DoesAdapter;
import com.example.eldercare.DayPlanner.MyDoes;
import com.example.eldercare.FoodActivity;
import com.example.eldercare.MedicationActivity;
import com.example.eldercare.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HealthActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Button btnAddNew;

    DrawerLayout drawerLayout;

    DatabaseReference reference;
    RecyclerView ourhealth;
    ArrayList<MyHealth> list;
    HealthAdapter healthAdapter;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.drawer);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        btnAddNew = findViewById(R.id.btnAddNew);

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HealthActivity.this, AddHealthActivity.class);
                startActivity(intent);
            }
        });

        //working with data
        ourhealth = findViewById(R.id.ourhealth);
        ourhealth.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<MyHealth>();

        //get data
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        String userId = firebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Users").child(userId).child("Health");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    MyHealth p = dataSnapshot1.getValue(MyHealth.class);
                    list.add(p);
                }
                healthAdapter = new HealthAdapter(HealthActivity.this, list);
                ourhealth.setAdapter(healthAdapter);
                healthAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        closeDrawer();
        switch (menuItem.getItemId()){
            case R.id.item_a:
                Intent intent = new Intent(HealthActivity.this, DayPlannerActivity.class);
                startActivityForResult(intent, 0);
                break;

            case R.id.item_b:
                intent  = new Intent(HealthActivity.this, MedicationActivity.class);
                startActivityForResult(intent, 0);
                break;

            case R.id.item_c:
                intent  = new Intent(HealthActivity.this, FoodActivity.class);
                startActivityForResult(intent, 0);
                break;

            case R.id.item_d:
                intent  = new Intent(HealthActivity.this, HealthActivity.class);
                startActivityForResult(intent, 0);
                break;

        }
        return false;
    }

    private void closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    private void openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            closeDrawer();
        }
        super.onBackPressed();
    }
}
