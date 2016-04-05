package com.example.abhiu.bars;


        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.view.MenuItemCompat;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.RatingBar;
        import android.widget.TextView;

        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Task1Frag extends Fragment {

    android.support.v7.widget.ShareActionProvider mShareActionProvider;
     HashMap<String, ?> movie;
    List<Map<String,?>> moviesList;
    private List<Map<String, ?>> myDataset;


    public List<Map<String, ?>> getMoviesList() {
        return moviesList;
    }

    public Task1Frag() {

        // Required empty public constructor
    }

    public static Task1Frag newInstance( Map<String,?> movieMap) {

        Bundle args = new Bundle();

        args.putInt("image" ,(Integer)movieMap.get("image"));
        args.putString("name", (String) movieMap.get("name"));
        args.putString("description",(String)movieMap.get("description"));
        args.putString("year" ,(String)movieMap.get("year"));
        args.putString("length" ,(String)movieMap.get("length"));
        args.putString("director", (String) movieMap.get("director"));
        args.putString("stars" ,(String)movieMap.get("stars"));
        args.putString("url" ,(String)movieMap.get("url"));
        args.putBoolean("selection", (Boolean) movieMap.get("selection"));
        args.putDouble("rating",(Double)movieMap.get("rating"));

        Task1Frag fragment = new Task1Frag();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task1, container, false);
        ImageView image = (ImageView)view.findViewById(R.id.img_movie_task1);
        TextView  text1=(TextView)view.findViewById(R.id.txt_movie_task1);
        TextView  text2=(TextView)view.findViewById(R.id.txt_movie_cast);
        TextView  text3=(TextView)view.findViewById(R.id.txt_movie_desc);
        TextView  text4=(TextView)view.findViewById(R.id.txt_movie_year);
        TextView  text6=(TextView)view.findViewById(R.id.txt_movie_len);
        TextView  text5=(TextView)view.findViewById(R.id.txt_movie_dir);
        RatingBar ratbar=(RatingBar)view.findViewById(R.id.ratingBar);

        Bundle args = getArguments();
        if(args != null) {
            image.setImageResource(args.getInt("image"));
            text1.setText(args.getString("name"));
            text2.setText(args.getString("stars"));
            text3.setText(args.getString("description"));
            text4.setText(args.getString("year"));
            text5.setText(args.getString("director"));
            text6.setText(args.getString("length"));
            ratbar.setRating((float) args.getDouble("rating"));
            ratbar.setMax(10);

        }
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            movie=(HashMap<String,?>) getArguments().getSerializable("movie");
        }
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater)
    {
        HashMap movie = new HashMap();
        inflater.inflate(R.menu.activity_task_2,menu);

        MenuItem shareItem=menu.findItem(R.id.share);
        mShareActionProvider=(android.support.v7.widget.ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        Intent intenshare=new Intent(Intent.ACTION_SEND);
        intenshare.setType("text/plain");
        intenshare.putExtra(Intent.EXTRA_TEXT,(String) movie.get("name"));
        mShareActionProvider.setShareIntent(intenshare);
        super.onCreateOptionsMenu(menu,inflater);
    }
}
