package com.example.studit.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface RetrofitInterface {

    //@Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("posting")
    Call<Model_PostAllList> getPostListByAll(@Header("Authorization") String auth);
}
