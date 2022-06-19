package com.example.studit.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AccessTokenServiceInterface {

    @POST("user/login")
    @FormUrlEncoded
    Call<TokenResponse> getToken(@Field("password") String password, @Field("phone") String phone);
}