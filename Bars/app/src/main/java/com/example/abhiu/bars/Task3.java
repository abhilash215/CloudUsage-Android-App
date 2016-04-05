package com.example.abhiu.bars;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3 extends AppCompatActivity implements Recyclerview_task3.onListItemSelectedListener {

    //MovieData movieData;
    ShareActionProvider mShareActionProvider;
    android.support.v7.app.ActionBar mActionBar;
    ScrollActionListener scrollActionListener;
    ScrollActionDownListener scrollActionDownListener;

    public Toolbar getToolbar2() {
        return toolbar2;
    }

    Toolbar toolbar2;
    Toolbar toolbar;
    List<Map<String,?>> moviesList;
    private List<Map<String, ?>> myDataset;

    public List<Map<String, ?>> getMoviesList() {
        return moviesList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task3);

        toolbar = (Toolbar) findViewById(R.id.toolbar_3);
        setSupportActionBar(toolbar);
         toolbar2 = (Toolbar) findViewById(R.id.toolbar_3_2);
        //  setSupportActionBar(toolbar2);
        toolbar2.inflateMenu(R.menu.toolbar_2_task3);
        setupToolbarItemSelected();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new Recyclerview_task3()).commit();
      //  movieData = new MovieData();
    }

    private void setupToolbarItemSelected() {
        toolbar2.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();
                AudioManager audioManager=((AudioManager)getSystemService(Context.AUDIO_SERVICE));
                switch (id) {
                    case R.id.task3_mic:

                        Toast.makeText(Task3.this, "Start Recording", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.task3_vu:
                        Toast.makeText(Task3.this, "Volume is increased", Toast.LENGTH_SHORT).show();
                        audioManager.adjustVolume(AudioManager.ADJUST_RAISE,0);
                        return true;

                    case R.id.task3_vd:
                        Toast.makeText(Task3.this, "Volume is decreased", Toast.LENGTH_SHORT).show();
                            audioManager.adjustVolume(AudioManager.ADJUST_LOWER, 0);
                        return true;

                    case R.id.task3_up:
                        Toast.makeText(Task3.this, "Scrolling to first movie position", Toast.LENGTH_SHORT).show();
                      //  int position = movieData.getSize();
                        if(scrollActionListener!=null) {
                            scrollActionListener.onScrollToTop();
                        }
                        return true;
                }
                return false;
            }
        });

                      // hide standalone toolbar
        toolbar2.setNavigationIcon(R.drawable.ic_visibility_off_black_18dp);
        toolbar2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Hiding  standalone Toolbar ", Toast.LENGTH_SHORT).show();
                toolbar2.setVisibility(View.GONE);
            }
        });

                              // display toolbar
        toolbar.setNavigationIcon(R.drawable.ic_visibility_black_18dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Standalone Toolbar Visible", Toast.LENGTH_SHORT).show();
                toolbar2.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onListItemSelected(int position, HashMap<String, ?> movie) {
      /*  FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, Task1Frag.newInstance(movieData.getItem(position)));
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.task3_toolbar_1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.abtme) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.task3_container, About_Me_Frag.newInstance(R.id.about_me))
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

        if (id == R.id.t3) {
            Intent i = new Intent(this, com.example.abhiu.bars.Task3.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(), "Launching Task 3 Activity", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.task3_t1_da) {

            Toast.makeText(getApplicationContext(), "Scrolling to last movie", Toast.LENGTH_SHORT).show();
            if (scrollActionDownListener != null) {
                scrollActionDownListener.onScrollToDown();

            }
            return true;
        }
            return super.onOptionsItemSelected(item);
        }



    public void setScrollActionListener(ScrollActionListener scrollActionListener) {
        this.scrollActionListener = scrollActionListener;
    }

    public void setScrollActionDownListener(ScrollActionDownListener scrollActionDownListener){
        this.scrollActionDownListener=scrollActionDownListener;
    }

    public interface ScrollActionListener{
        void onScrollToTop();
    }

    public interface ScrollActionDownListener{
    void onScrollToDown();
    }
}
