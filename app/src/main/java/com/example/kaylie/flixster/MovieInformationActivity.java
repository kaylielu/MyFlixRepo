package com.example.kaylie.flixster;

import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class MovieInformationActivity extends AppCompatActivity {

    RatingBar ratingBar;
    double rating;
    double popularity;
    String synopsis;
    String title;
    String posterPath;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_information);

        //actionBar.setIcon(R.drawable.airbnb_icon);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        rating = getIntent().getDoubleExtra("rating",0);
        popularity = getIntent().getDoubleExtra("popularity",0);
        synopsis = getIntent().getStringExtra("synopsis");
        title = getIntent().getStringExtra("title");
        posterPath = getIntent().getStringExtra("poster");


        ratingBar = (RatingBar)findViewById(R.id.rbRatingBar);
        if(ratingBar !=null)
        ratingBar.setRating((float)(rating/2));

        Drawable progress = ratingBar.getProgressDrawable();
        DrawableCompat.setTint(progress, Color.WHITE);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/GREEB___.ttf");



        TextView titleView = (TextView)findViewById(R.id.tvTitle);
        titleView.setText(title);

        titleView.setTypeface(custom_font);

        TextView synopsisView = (TextView)findViewById(R.id.tvSynopsis);
        synopsisView.setText(synopsis);

        TextView popularityView = (TextView)findViewById(R.id.tvPopularity);
        popularityView.setText("" + popularity);

        ImageView posterBackground = (ImageView)findViewById(R.id.imageView);

        Picasso.with(this).load(posterPath).placeholder(R.drawable.movie).into(posterBackground);
        posterBackground.setScaleType(ImageView.ScaleType.FIT_XY);
        posterBackground.setAdjustViewBounds(true);


        if(this.getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT) {

        }else {

            titleView.setTextSize(15);
            ratingBar.setPadding(0,0,0,0);

        }


    }
}
