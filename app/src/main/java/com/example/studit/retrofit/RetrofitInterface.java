package com.example.studit.retrofit;

import com.example.studit.retrofit.home.ModelHomeList;
import com.example.studit.retrofit.search.ModelPostAllList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @GET("posting")
    Call<ModelPostAllList> getPostListByAll(@Header("Authorization") String auth);

    @GET("posting/search/filter")
    Call<ModelPostAllList> getPostListByFilter(@Query("activities") String[] activities, @Query("genders") String[] genders,
                                               @Query("provinces") String[] provinces, @Query("targets") String[] targets, @Header("Authorization") String auth);

    @GET("home")
    Call<ModelHomeList> getHomeList(@Header("Authorization") String auth);
}
