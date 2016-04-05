package com.example.abhiu.bars;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;
import jp.wasabeef.recyclerview.animators.LandingAnimator;

/**
 * Created by abhiu on 2/23/2016.
 */
public class Recyclerview_task3 extends Fragment implements AdapterView.OnItemClickListener {


    final Firebase ref = new Firebase("https://cloud09.firebaseio.com/");
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    MyFirebaseRecylerAdapter myFirebaseRecyclerViewAdapter;
    //  MovieData movieData;
    // MyRecyclerViewAdapter.OnListItemSelected mListener;
    private onListItemSelectedListener mListener;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (onListItemSelectedListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement PageListener");
        }
    }


    public Recyclerview_task3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootview = inflater.inflate(R.layout.frag_recyclerview_task3, container, false);
        mRecyclerView = (RecyclerView) rootview.findViewById(R.id.cardList);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        //     movieData = new MovieData();
      //  myFirebaseRecyclerViewAdapter = new MyFirebaseRecylerAdapter();
        mRecyclerView.setAdapter(myFirebaseRecyclerViewAdapter);

        ((Task3) getActivity()).setScrollActionListener(new Task3.ScrollActionListener() {
            @Override
            public void onScrollToTop() {
                mRecyclerView.smoothScrollToPosition(0);
            }
        });

        ((Task3) getActivity()).setScrollActionDownListener(new Task3.ScrollActionDownListener() {
            @Override
            public void onScrollToDown() {
                mRecyclerView.smoothScrollToPosition(30);
            }
        });


        // on click function/////////////////////////////////////////////////
        myFirebaseRecyclerViewAdapter.setOnItemClickListener(new MyFirebaseRecylerAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View v, int position) {


            }

            @Override
            public void onOverflowMenuClick(View v, final int position) {
                final PopupMenu popup = new PopupMenu(getActivity(), v);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.dup_ov:
                                // overflow
                                Movie cloud = myFirebaseRecyclerViewAdapter.getItem(position);
                                cloud.setName(cloud.getName() + "_NEW");
                                cloud.setId(cloud.getId() + "_new");
                                ref.child(cloud.getId()).setValue(cloud);

                                return true;

                            case R.id.del_ov:
                                Movie cloudDelete = myFirebaseRecyclerViewAdapter.getItem(position);
                                ref.child(cloudDelete.getId()).removeValue();
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
                  //  int position = movieData.find(query);
                    //if (position >= 0)
                      //  mRecyclerView.scrollToPosition(position);
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

    // Item default Animation
    private void defaultAnimation() {
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(2000);
        animator.setRemoveDuration(1000);
        mRecyclerView.setItemAnimator(animator);
    }

    // Item Animation         //wasabeef library
    private void fancyAnimation() {
        LandingAnimator animator = new LandingAnimator();
        animator.setInterpolator(new OvershootInterpolator());
        animator.setAddDuration(2000);
        animator.setRemoveDuration(1000);
        mRecyclerView.setItemAnimator(animator);
    }

    // Adapter Animation                   //wasabeef library
    private void adapteranimation() {
        AlphaInAnimationAdapter alphaadapter = new AlphaInAnimationAdapter(myFirebaseRecyclerViewAdapter);
        SlideInBottomAnimationAdapter scaleadapter = new SlideInBottomAnimationAdapter(alphaadapter);
        SlideInRightAnimationAdapter radapter = new SlideInRightAnimationAdapter(scaleadapter);
        mRecyclerView.setAdapter(scaleadapter);
    }


    public int find(String query) {
        HashMap movie = new HashMap();
        for(int i=0;i<movie.size();i++)
        {
            if(query==movie.get(i));
            return i;
        }
        return 0;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    public interface onListItemSelectedListener {
        public void onListItemSelected(int position, HashMap<String, ?> movie);
    }
}

