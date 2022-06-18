package com.example.studit.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
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

    @GET("user/check")
    Call<Model_ValidatePhone> getValidatePhone(@Query("phone") String phone);

   // @FormUrlEncoded
   // @POST("user/join")
   // Call<Model_UserJoin> getUserJoin(@Field("isSuccess") Boolean isSuccess, @Field("message") String message);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @FormUrlEncoded
    @POST("user/join")
    Call<Model_UserJoin2> getUserJoin(@Field("email") String email,
                                      @Field("password") String password,
                                      @Field("phone") String phone,
                                      @Field("userName") String userName);

}
