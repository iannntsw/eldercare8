package com.example.eldercare;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.eldercare.DayPlanner.DayPlannerActivity;
import com.example.eldercare.HealthMonitor.HealthActivity;

public class FoodActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;

    Button deliverooBtn, foodPandaBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        deliverooBtn = findViewById(R.id.deliverooBtn);
        foodPandaBtn = findViewById(R.id.foodPandaBtn);

        deliverooBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://deliveroo.com.sg/"));
                startActivity(intent);
            }
        });

        foodPandaBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.foodpanda.sg/"));
                startActivity(intent);
            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.drawer);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        closeDrawer();
        switch (menuItem.getItemId()){
            case R.id.item_a:
                Intent intent = new Intent(FoodActivity.this, DayPlannerActivity.class);
                startActivityForResult(intent, 0);
                break;

            case R.id.item_b:
                intent  = new Intent(FoodActivity.this, MedicationActivity.class);
                startActivityForResult(intent, 0);
                break;

            case R.id.item_c:
                intent  = new Intent(FoodActivity.this, FoodActivity.class);
                startActivityForResult(intent, 0);
                break;

            case R.id.item_d:
                intent  = new Intent(FoodActivity.this, HealthActivity.class);
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
