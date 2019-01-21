package com.system.perfect.moviecatalogsubm2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.system.perfect.moviecatalogsubm2.R;
import com.system.perfect.moviecatalogsubm2.model.Movie;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Movie> movieList;

    public MoviesAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(ArrayList<Movie> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.ViewHolder viewHolder, int position) {
        viewHolder.textTitle.setText(movieList.get(position).getTitle());
        Glide.with(context)
                .load("http://image.tmdb.org/t/p/w185/" + getMovieList().get(position).getPoster_path())
                .apply(new RequestOptions().override(350, 550))
                .into(viewHolder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgPoster;
        private TextView textTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.img_item_photo);
            textTitle = itemView.findViewById(R.id.movie_title);
        }
    }
}
