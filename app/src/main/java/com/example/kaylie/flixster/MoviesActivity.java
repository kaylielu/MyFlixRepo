package com.example.kaylie.flixster;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kaylie.flixster.adapters.MovieArrayAdapter;
import com.example.kaylie.flixster.models.Movie;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class MoviesActivity extends AppCompatActivity {

    ArrayList<Movie> movies;
    MovieArrayAdapter movieAdapter;
    ListView lvItems;
    private SwipeRefreshLayout swipeContainer;
    AsyncHttpClient client;
    String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);

        //actionBar.setIcon(R.drawable.airbnb_icon);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        swipeContainer.setOnRefreshListener(new OnRefreshListener(){

            @Override
            public void onRefresh(){
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                //once the network request has completed successfully.
                fetchTimelineAsync();
            }

        });

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);



        movies = new ArrayList<>();
        movieAdapter = new MovieArrayAdapter(this,movies);
        lvItems = (ListView)findViewById(R.id.lvMovies);
        lvItems.setAdapter(movieAdapter);

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<? > arg0, View item, int pos,
                                    long id) {

                Intent expose = new Intent(MoviesActivity.this, MovieInformationActivity.class);
                expose.putExtra("pos", pos);
                expose.putExtra("rating", movies.get(pos).getRating());
                expose.putExtra("synopsis", movies.get(pos).getOverview());
                expose.putExtra("popularity", movies.get(pos).getPopularity());
                expose.putExtra("title", movies.get(pos).getOriginalTitle());
                expose.putExtra("poster", movies.get(pos).getPosterPath());
                startActivity(expose);
            }

        });

        client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                JSONArray movieJsonResults = null;
                try {
                    movieJsonResults = response.getJSONArray("results");
                    movies.addAll(Movie.fromJSONArray(movieJsonResults));
                    movieAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });

//        // 1. Get the actual movi
//        // 2. Get the ListView that we want to populate
//        ListView lvMovies = (ListView)findViewById(R.id.lvMovies);
//        // 3. Create an ArrayAdapter
//        //ArrayAdapter<Movie> adapter = new ArrayAdapter<Movie>(this, android.R.layout.simple_list_item_1, movies);
//        MoviesAdapter adapter = new MoviesAdapter(this, movies);
//        // 4. Associate the adapter with the ListView
//        if (lvMovies != null) {
//            lvMovies.setAdapter(adapter);
//        }

    }


    public void fetchTimelineAsync() {
        // Send the network request to fetch the updated data
        // `client` here is an instance of Android Async HTTP
        client.get(url, new JsonHttpResponseHandler() {
            JSONArray movieJsonResults = null;
            public void onSuccess(int statuscode, Header[] headers, JSONObject response) {
                // Remember to CLEAR OUT old items before appending in the new ones
                movieAdapter.clear();

                try {
                    movieJsonResults = response.getJSONArray("results");
                    // ...the data has come back, add new items to your adapter...
                    movieAdapter.addAll(Movie.fromJSONArray(movieJsonResults));
                    movieAdapter.notifyDataSetChanged();
                    // Now we call setRefreshing(false) to signal refresh has finished
                    swipeContainer.setRefreshing(false);
            }catch(JSONException e){
                    e.getStackTrace();
                }
                }


            public void onFailure(Throwable e) {
                Log.d("DEBUG", "Fetch timeline error: " + e.toString());
            }
        });
    }


}
