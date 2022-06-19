package com.example.studit.login;

import com.example.studit.retrofit.ModelAuth;
import com.example.studit.retrofit.Model_UserLogIn;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @POST("user/login")
    Call<ModelAuth> createPost(@Body Model_UserLogIn login);
}
