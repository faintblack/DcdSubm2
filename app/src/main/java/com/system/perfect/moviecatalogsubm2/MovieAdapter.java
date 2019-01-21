package com.system.perfect.moviecatalogsubm2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private Context context;
    private ArrayList<MovieItem> listMovie;

    public MovieAdapter(Context context, ArrayList<MovieItem> movieList) {
        this.context = context;
        this.listMovie = movieList;
    }
    public ArrayList<MovieItem> getListMovie() {
        return listMovie;
    }
    public void setListMovie(ArrayList<MovieItem> listMovie) {
        this.listMovie = listMovie;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        ImageView imgPoster;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            imgPoster = itemView.findViewById(R.id.img_item_photo);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Glide.with(context)
                .load("http://image.tmdb.org/t/p/w185/" + getListMovie().get(position).getPosterPath())
                .apply(new RequestOptions().override(350, 550))
                .into(viewHolder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return getListMovie().size();
    }

    public void updateMovie(ArrayList<MovieItem> item){
        listMovie = item;
        notifyDataSetChanged();
    }

    private MovieItem getItem(int position){
        return listMovie.get(position);
    }

}
