package com.system.perfect.moviecatalogsubm2.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataInterface {

    @GET("/3/movie/now_playing?api_key=a9f9c29a163472817de4426b1c8f62c7&language=en-US&page=1")
    Call<JSONResult> getNowPlaying();

}
