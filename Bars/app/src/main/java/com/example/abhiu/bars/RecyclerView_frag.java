package com.example.abhiu.bars;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.LruCache;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;
import jp.wasabeef.recyclerview.animators.ScaleInTopAnimator;

import static com.example.abhiu.bars.MyFirebaseRecylerAdapter.MovieViewHolder;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerView_frag extends Fragment implements AdapterView.OnItemClickListener {

   final Firebase ref=new Firebase("https://cloud09.firebaseio.com/moviedata");
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    List<Map<String, ?>> moviesList;
    MyFirebaseRecylerAdapter myFirebaseRecyclerViewAdapter;
    LruCache<String, Bitmap> mImgMemoryCache;

     public RecyclerView_frag() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootview = inflater.inflate(R.layout.fragment_recycler_view_frag, container, false);
        mRecyclerView = (RecyclerView) rootview.findViewById(R.id.cardList);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        myFirebaseRecyclerViewAdapter = new MyFirebaseRecylerAdapter(Movie.class, R.layout.cardview_layout, MovieViewHolder.class, ref, getActivity());
        mRecyclerView.setAdapter(myFirebaseRecyclerViewAdapter);

        // on click function/////////////////////////////////////////////////
        myFirebaseRecyclerViewAdapter.setOnItemClickListener(new MyFirebaseRecylerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
            }

            @Override
            public void onOverflowMenuClick(View v, final int position) {
                final PopupMenu popup = new PopupMenu(getActivity(), v);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    HashMap movie;
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Movie addmovie;
                        Movie removemovie;
                        switch (item.getItemId()) {
                            case R.id.dup_ov:
                                addmovie = myFirebaseRecyclerViewAdapter.getItem(position);
                               // movie.put("id",((String)movie.get("id"))+"_new");
                                addmovie.setId(addmovie.getId() + "_new");
                                ref.child(addmovie.getId()).setValue(addmovie);
                                return true;

                            case R.id.del_ov:
                                removemovie = myFirebaseRecyclerViewAdapter.getItem(position);
                                ref.child(removemovie.getId()).removeValue();
                                return true;

                            default:
                                return false;

                        }
                    }

                });
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu_overflow, popup.getMenu());
                popup.show();
            }


        });

        // defaultAnimation();
        fancyAnimation();
        adapteranimation();
        return rootview;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.activity_task_2, menu);
        SearchView search=(SearchView)menu.findItem(R.id.search).getActionView();
        if(search!=null){
            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    mRecyclerView.scrollToPosition(0);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String query) {
                    return true;
                }
            });

        }
        super.onCreateOptionsMenu(menu, inflater);

    }

    final

    // Item default Animation
    private void defaultAnimation() {
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(1500);
        animator.setRemoveDuration(100);
        mRecyclerView.setItemAnimator(animator);
    }

    // Item Animation         //wasabeef library
    private void fancyAnimation() {
        ScaleInTopAnimator animator = new ScaleInTopAnimator();
        animator.setInterpolator(new OvershootInterpolator());
        animator.setAddDuration(2000);
        animator.setRemoveDuration(1500);
        mRecyclerView.setItemAnimator(animator);
    }

    // Adapter Animation                   //wasabeef library
    private void adapteranimation() {
        AlphaInAnimationAdapter alphaadapter = new AlphaInAnimationAdapter(myFirebaseRecyclerViewAdapter);
        SlideInBottomAnimationAdapter scaleadapter = new SlideInBottomAnimationAdapter(alphaadapter);
        SlideInRightAnimationAdapter radapter = new SlideInRightAnimationAdapter(scaleadapter);
        mRecyclerView.setAdapter(scaleadapter);
    }

    public void initializeDataFromCloud(){
moviesList.clear();

    }



    class ActionBarCallBack implements android.view.ActionMode.Callback {
        int position;

        public ActionBarCallBack(int position) {
            this.position = position;
        }

        @Override
        public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.contextual_menu_2, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {

            return false;
        }

        @Override
        public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
            int id = item.getItemId();
            switch (id) {
                case R.id.item_delete:
                    break;

                case R.id.item_duplicate:
                    mode.finish();
                    break;
                default:
                    break;

            }
            return false;
        }

        @Override
        public void onDestroyActionMode(android.view.ActionMode mode) {

        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    public interface onListItemSelectedListener {
        public void onListItemSelected(int position, HashMap<String, ?> movie);
    }
  }






