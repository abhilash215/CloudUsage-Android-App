package com.example.abhiu.bars;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class About_Me_Frag extends Fragment {


    public About_Me_Frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about__me_, container, false);
    }

    private static final String ARG_SECTION_NUMBER = "section_number";


    public static final About_Me_Frag newInstance(int sectionNumber) {
        About_Me_Frag fragment = new About_Me_Frag();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
}
