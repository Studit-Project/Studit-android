package com.example.studit.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitInterface {
    String SERVER_URL = "";

    @GET("posts/{id}")
        // 모든 유저의 id값만 받아오는 메서드(id 중복체크를 위해) //테스트용
    Call<model_test> getName(@Path("id") String post);
}
