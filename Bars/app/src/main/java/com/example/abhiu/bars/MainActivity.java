package com.example.abhiu.bars;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);
        this.setTitle("Bars_Cloud");
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.abtme) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.drawer_layout, About_Me_Frag.newInstance(R.id.about_me))
                    .addToBackStack(null)
                    .commit();
            Toast.makeText(getApplicationContext(), "Loading 'About Me' fragment", Toast.LENGTH_SHORT).show();

        } else {
            if (id == R.id.t2) {
                Intent i = new Intent(this, com.example.abhiu.bars.Task2_t.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Launching Task 2 Activity", Toast.LENGTH_SHORT).show();
                }

            else if (id == R.id.t3) {

                Intent i = new Intent(this, com.example.abhiu.bars.Task3.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Launching Task 3 Activity", Toast.LENGTH_SHORT).show();
                // Toast.makeText(getApplicationContext(), "Launched", Toast.LENGTH_LONG).show();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (getSupportFragmentManager().getBackStackEntryCount()>0) {
            outState.putInt("activity_main", 1);
        }
        super.onSaveInstanceState(outState);
    }
}

//}
