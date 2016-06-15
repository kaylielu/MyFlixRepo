package com.example.kaylie.flixster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;

public class MovieInformation extends AppCompatActivity {

    RatingBar rating;
    double popularity;
    String synopsis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_information);

    }
}
