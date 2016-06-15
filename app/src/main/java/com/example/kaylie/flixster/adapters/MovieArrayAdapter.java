package com.example.kaylie.flixster.adapters;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kaylie.flixster.R;
import com.example.kaylie.flixster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by kaylie on 6/15/16.
 */
public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    public MovieArrayAdapter(Context context, List<Movie> movies){
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    private static class ViewHolder{
        TextView name;
        TextView overview;
        ImageView image;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Get the data item for the posiiton
        Movie movie = getItem(position);

        ViewHolder viewHolder;
        //check the existing view being reused
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
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
//        // find the image view
//        ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivPoster);
//        ivImage.setImageResource(0);

//        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
//        TextView tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);

//        tvTitle.setText(movie.getOriginalTitle());
//        tvOverview.setText(movie.getOverview());

        // Populate the data into the template view using the data object
        //tvTitle.setText(movie.title);

        if(getContext().getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT) {
            Picasso.with(getContext()).load(movie.getPosterPath()).placeholder(R.drawable.movie).into(viewHolder.image);
        }else {
            Picasso.with(getContext()).load(movie.getBackDropPath()).placeholder(R.drawable.movie).into(viewHolder.image);
        }


        return convertView;

    }
}
