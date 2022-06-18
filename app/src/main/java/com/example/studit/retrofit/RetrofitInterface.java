package com.example.studit.retrofit;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitInterface {

    //@Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("posting")
    Call<Model_PostAllList> getPostListByAll(@Header("Authorization") String auth);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("user/check")
    Call<Model_ValidatePhone> getValidatePhone(@Query("phone") String phone);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("user/join")
    Call<Model_UserJoin> getUserJoin(@Body Model_UserJoin userJoin2);

}
