package com.example.invenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home_page);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.include);

        //setting toolbar as actionbar
        setSupportActionBar(toolbar);


        //to make click on items
        navigationView.bringToFront();

        //assigning drawable action bar
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);


    }

    //when back button is pressed drawer will close

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_computer:
                Intent intent = new Intent(HomePage.this, Computer.class);
                startActivity(intent);
                break;
            case R.id.nav_security:
                Intent intent1 = new Intent(HomePage.this, Security.class);
                startActivity(intent1);
                break;



            case R.id.nav_share:
                //Toast.makeText(HomePage.this, "Share", Toast.LENGTH_SHORT).show();
                Intent shareintent=new Intent();
                shareintent.setAction(Intent.ACTION_SEND);
                shareintent.putExtra(Intent.EXTRA_TEXT,"Please share this application");
                shareintent.setType("text/plain");
                startActivity(Intent.createChooser(shareintent,"Share via"));
                break;


            case R.id.nav_logout:
                Intent intent2 = new Intent(HomePage.this, MainActivity.class);
                startActivity(intent2);
                break;

            case R.id.nav_profile:
                Intent intent3 = new Intent(HomePage.this, UserProfile.class);
                startActivity(intent3);
                break;
            case R.id.nav_contact:
                Intent intent4 = new Intent(HomePage.this, Contact.class);
                startActivity(intent4);
                break;

            case R.id.nav_rate:
                Intent intent5 = new Intent(HomePage.this,Rating.class);
                startActivity(intent5);
                break;


        }
        return true;
    }
}