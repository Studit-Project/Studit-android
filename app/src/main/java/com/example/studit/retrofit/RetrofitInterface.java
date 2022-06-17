package com.example.studit.retrofit;

import java.lang.reflect.Array;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @GET("posting")
    Call<Model_PostAllList> getPostListByAll(@Header("Authorization") String auth);

    @GET("posting/search/filter")
    Call<Model_PostAllList> getPostListByFilter(@Query("activities") String[] activities, @Query("genders") String[] genders,
                                                @Query("provinces") String[] provinces, @Query("targets") String[] targets, @Header("Authorization") String auth);

}
