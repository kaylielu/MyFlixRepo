package com.example.kaylie.flixster;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class MovieInformation extends AppCompatActivity {

    RatingBar ratingBar;
    double rating;
    double popularity;
    String synopsis;
    String title;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_information);

        //actionBar.setIcon(R.drawable.airbnb_icon);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.movie_icon);
        rating = getIntent().getDoubleExtra("rating",0);
        popularity = getIntent().getDoubleExtra("popularity",0);
        synopsis = getIntent().getStringExtra("synopsis");
        title = getIntent().getStringExtra("title");

        ratingBar = (RatingBar)findViewById(R.id.rbRatingBar);
        if(ratingBar !=null)
        ratingBar.setRating((float)(rating/2));

        TextView titleView = (TextView)findViewById(R.id.tvTitle);
        titleView.setText(title);

        TextView synopsisView = (TextView)findViewById(R.id.tvSynopsis);
        synopsisView.setText(synopsis);

        TextView popularityView = (TextView)findViewById(R.id.tvPopularity);
        popularityView.setText("" + popularity);

        if(this.getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT) {

        }else {

            titleView.setTextSize(15);
            ratingBar.setPadding(0,0,0,0);

        }


    }
}
