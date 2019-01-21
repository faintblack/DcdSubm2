package com.system.perfect.moviecatalogsubm2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.system.perfect.moviecatalogsubm2.adapter.MoviesAdapter;
import com.system.perfect.moviecatalogsubm2.model.Movie;
import com.system.perfect.moviecatalogsubm2.support.ItemClickSupport;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private String API = "a9f9c29a163472817de4426b1c8f62c7";
    private RecyclerView rvMovie;
    final ArrayList<Movie> movieItemses = new ArrayList<>();
    private MoviesAdapter adapter;

    private String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=" + API + "&language=en-US&page=1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvMovie = findViewById(R.id.rv_movie);
        rvMovie.setHasFixedSize(true);

        // Mengambil data sekaligus memasukkan data kedalam array movieItemses
        requestMovieData();

        // Implementasi data ke recyclerview
        showDataList();
    }

    private void showSelectedMovie(Movie data){
        Toast.makeText(this, "Kamu memilih "+data.getTitle(), Toast.LENGTH_SHORT).show();
    }

    public void showDataList(){
        rvMovie.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new MoviesAdapter(this);
        adapter.setMovieList(movieItemses);
        rvMovie.setAdapter(adapter);

        ItemClickSupport.addTo(rvMovie).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedMovie(movieItemses.get(position));
            }
        });
    }

    private void requestMovieData() {

        //RequestQueue initialized
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        StringRequest mStringRequest = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray data = obj.getJSONArray("results");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject movie = data.getJSONObject(i);
                        Movie item = new Movie(movie);
                        movieItemses.add(item);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "ErrorResponse :" + error.toString());
            }
        });
        mRequestQueue.add(mStringRequest);
    }


}
