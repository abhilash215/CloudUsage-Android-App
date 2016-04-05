package com.example.abhiu.bars;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseRecyclerAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

public class MyFirebaseRecylerAdapter extends FirebaseRecyclerAdapter<Movie,MyFirebaseRecylerAdapter.MovieViewHolder> {

    public List<Map<String, ?>> myDataset;
    public Context mContext ;
  // MyFirebaseRecylerAdapter myFirebaseRecyclerViewAdapter;
static MyFirebaseRecylerAdapter.OnItemClickListener myItemclickListener;
    public MyFirebaseRecylerAdapter(Class<Movie> modelClass, int modelLayout,
                                    Class<MovieViewHolder> holder, Query ref,Context context) {
        super(modelClass, modelLayout, holder, ref);
        this.mContext = context;
    }

    @Override
    protected void populateViewHolder(MovieViewHolder movieViewHolder, Movie movie, int i) {

        //TODO: Populate viewHolder by setting the movie attributes to cardview fields
        //movieViewHolder.nameTV.setText(movie.getName());
        movieViewHolder.vTitle.setText(movie.getName());
        movieViewHolder.vDescription.setText(movie.description);
        Float rating=movie.getRating()/2;
        movieViewHolder.ratbar.setRating(rating);
        Picasso.with(mContext).load(movie.getUrl()).into(movieViewHolder.vIcon);
    }

    public void setOnItemClickListener(final OnItemClickListener ItemClickListener){
        myItemClickListener = ItemClickListener;
    }


    //TODO: Populate ViewHolder and add listeners.
    public static class MovieViewHolder extends RecyclerView.ViewHolder{
        public ImageView vIcon;
        public TextView vTitle;
        public TextView vDescription;
        public CheckBox vCheckBox;
        public RatingBar ratbar;
        public ImageView vMenu;

    public MovieViewHolder(View v) {
            super(v);
        vIcon = (ImageView) v.findViewById(R.id.img_icon);
        vTitle = (TextView) v.findViewById(R.id.txt_title);
        vDescription = (TextView) v.findViewById(R.id.txt_description);
        ratbar=(RatingBar)v.findViewById(R.id.ratingBar);
        vMenu=(ImageView)v.findViewById(R.id.img_of);
        vCheckBox = (CheckBox) v.findViewById(R.id.chk_selection);


        v.setOnClickListener(new View.OnClickListener() {
            @Override
                    public void onClick(View v)
            {
                if(myItemclickListener!=null){
                    myItemclickListener.onItemClick(v,getPosition());
                }
            }
        });

        if(vMenu!=null ){
            vMenu.setOnClickListener(new View.OnClickListener(){
             public  void onClick(View v){
                    if(myItemClickListener!=null)  myItemClickListener.onOverflowMenuClick(v,getPosition());
                }
            });
        }

    }
    }

    public interface OnItemClickListener {

        public void onItemClick(View v, int position);

        public void onOverflowMenuClick(View v, int position);

    }
    public static OnItemClickListener myItemClickListener;

}
