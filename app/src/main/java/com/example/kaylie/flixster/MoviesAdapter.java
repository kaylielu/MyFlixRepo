package com.example.kaylie.flixster;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.kaylie.flixster.models.Movie;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by kaylie on 6/15/16.
 */
public class MoviesAdapter extends ArrayAdapter<Movie> {

    private static class ViewHolder{
        TextView name;
        TextView overview;
        ImageView image;
    }

    public MoviesAdapter(Context context, ArrayList<Movie> movies) {
        super(context, R.layout.item_movie, movies);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position).getRating()>=5) {
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Movie movie = getItem(position);
//
        ViewHolder viewHolder;
        Log.d("entered", "true");

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());

            convertView = inflater.inflate(R.layout.item_movie,null);
//            int type = getItemViewType(position);
//            convertView = getInflatedLayoutForType(type);
//            Log.d("view", "the number is " + type);
            viewHolder.name = (TextView)convertView.findViewById(R.id.tvTitle);
            viewHolder.overview = (TextView)convertView.findViewById(R.id.tvOverview);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.ivPoster);





            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.name.setText(movie.getOriginalTitle());
        viewHolder.overview.setText(movie.getOverview());
        viewHolder.image.setImageResource(0);


        if(getContext().getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT) {
            Picasso.with(getContext()).load(movie.getPosterPath()).transform(new RoundedCornersTransformation(10, 10)).placeholder(R.drawable.movie).into(viewHolder.image);
        }else {
            Picasso.with(getContext()).load(movie.getBackDropPath()).transform(new RoundedCornersTransformation(10, 10)).placeholder(R.drawable.movie).into(viewHolder.image);
        }


        // Return the completed view to render on screen
        return convertView;
    }

    private View getInflatedLayoutForType(int type){
        if (type == 1){
            return LayoutInflater.from(getContext()).inflate(R.layout.item_movie_popular, null);
        }else{
            return LayoutInflater.from(getContext()).inflate(R.layout.item_movie,null);
        }
    }



}

