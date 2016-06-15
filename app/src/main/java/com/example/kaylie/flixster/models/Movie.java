package com.example.kaylie.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kaylie on 6/15/16.
 */
public class Movie {


    String posterPath;
    String originalTitle;
    String overview;
    String backDropPath;
    double rating;

    public Movie(JSONObject jsonObject)throws JSONException {
        this.posterPath = jsonObject.getString("poster_path");
        this.originalTitle = jsonObject.getString("original_title");
        this.overview = jsonObject.getString("overview");
        this.backDropPath = jsonObject.getString("backdrop_path");
        this.rating = jsonObject.getDouble("vote_average");
    }

    public static ArrayList<Movie>fromJSONArray(JSONArray array) {
        ArrayList<Movie> results = new ArrayList<>();
        for (int x = 0; x < array.length(); x++) {

            try {
                results.add(new Movie(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();

            }
        }
        return results;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getBackDropPath(){
        return String.format("https://image.tmdb.org/t/p/w342/%s", backDropPath);
    }

    public Double getRating(){
        return rating;
    }

}
