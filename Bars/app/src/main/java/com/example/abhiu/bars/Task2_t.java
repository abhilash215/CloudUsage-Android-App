package com.example.abhiu.bars;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.HashMap;

public class Task2_t extends AppCompatActivity implements RecyclerView_frag.onListItemSelectedListener {
    //MovieData movieData;
    RecyclerView mRecyclerView;
    android.support.v7.widget.ShareActionProvider mShareActionProvider;
    android.support.v7.app.ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task2_t);
        this.setTitle("Task2");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // load  recyclerview  fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.Task2_container, new RecyclerView_frag()).commit();
     //   movieData = new MovieData();
    }


    @Override
    public void onListItemSelected(int position, HashMap<String, ?> movie) {
/*
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.Task2_container, Task1Frag.newInstance(movieData.getItem(position)));
        transaction.addToBackStack("card_view");
        transaction.commit();*/
    }

    @Nullable
    //@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_task1, null);
        return rootView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.abtme) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.Task2_container, About_Me_Frag.newInstance(R.id.about_me))
                    .commit();
            Toast.makeText(getApplicationContext(), "Loading 'About Me' fragment", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.t2) {
            Intent i = new Intent(this, com.example.abhiu.bars.Task2_t.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(), "Launching Task 2 Activity", Toast.LENGTH_SHORT).show();
            return true;
        }

        if(id==R.id.t3){
            Intent i = new Intent(this, com.example.abhiu.bars.Task3.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(), "Launching Task 3 Activity", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




}
