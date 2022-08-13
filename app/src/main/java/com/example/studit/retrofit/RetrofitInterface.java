package com.example.studit.retrofit;

import com.example.studit.login.LoginRequest;
import com.example.studit.login.LoginResponse;
import com.example.studit.login.LoginResponseList;
import com.example.studit.retrofit.home.ModelHomeResult;
import com.example.studit.retrofit.home.profile.ModelProfileResult;
import com.example.studit.retrofit.home.profile.setting.Model_UserNick;
import com.example.studit.retrofit.join.Model_UserId;
import com.example.studit.retrofit.join.Model_UserJoin;
import com.example.studit.retrofit.join.Model_ValidatePhone;
import com.example.studit.retrofit.search.ModelPostAllList;
import com.example.studit.retrofit.study.ModelStudyDetail;
import com.example.studit.retrofit.studyhome.ModelStudyList;
import com.example.studit.retrofit.studyhome.ModelStudyListAll;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

//import com.example.studit.retrofit.home.ModelProfile;

//import com.example.studit.retrofit.home.ModelProfile;

public interface RetrofitInterface {

    //@Headers({ "Content-Type: application/json;charset=UTF-8"})
//    @GET("posting")
//    Call<Model_PostAllList> getPostListByAll(@Header("Authorization") String auth);

//    @FormUrlEncoded
//    @POST("user/login")
//    Call<LoginResponse> getLoginResponse(
//            @Field("phone") String phone,
//            @Field("password") String password,
//
//            @Body LoginRequest loginRequest
//    );

//    public interface initMyApi {
//        @POST("user/login")
//        Call<LoginResponse> getLoginResponse(@Body LoginRequest loginRequest);
//    }

    //posting
    @GET("posting")
    Call<ModelPostAllList> getPostListByAll(@Header("Authorization") String auth);

    @GET("posting/search/filter")
    Call<ModelPostAllList> getPostListByFilter(@Query("activities") String[] activities, @Query("genders") String[] genders,
                                               @Query("provinces") String[] provinces, @Query("targets") String[] targets, @Header("Authorization") String auth);

    @GET("posting/search/{keyword}")
    Call<ModelPostAllList> getPostListByFilterKeyword(@Path("keyword") String keyword, @Header("Authorization") String auth);

    //study
    @GET("study/{studyId}")
    Call<ModelStudyDetail> getStudyByStudyId(@Path("studyId") int studyId, @Header("Authorization") String auth);

    @DELETE("study/{studyId}")
    Call<Void> deleteStudyByStudyId(@Path("studyId") int studyId, @Header("Authorization") String auth);

    @POST("study/{studyId}/recruitment")
    Call<Void> postNewStudyMemberByStudyId(@Path("studyId") int studyId, @Body String nickname, @Header("Authorization") String auth);

    @DELETE("study/{studyId}/expulsion/{followerId}")
    Call<Void> deleteStudyMemberByStudyId(@Path("studyId") int studyId, @Path("followerId") int followerId, @Header("Authorization") String auth);

    //studyhome
    @FormUrlEncoded
    @GET("study/management")
    Call<ModelStudyListAll> getData(@Header("Authorization") String auth);
    @GET("study/management")
    Call<ModelStudyList> getDataList(@Body ModelStudyList modelStudyList);

    //home
    @GET("home")
    Call<ModelHomeResult> getHomeList(@Header("Authorization") String auth);

    //login
    @POST("user/login")
    Call<ModelAuth> postUserLogin(@Body Model_UserLogIn modelUserLogIn);

    //login
    public interface initMyApi {
        @POST("/user/login")
        Call<LoginResponse> getLoginResponse(@Body LoginRequest loginRequest);

        @POST("/user/login")
        Call<LoginResponseList> getLoginResponseList(@Body LoginResponseList loginResponseList);
    }

    //join
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("user/check")
    Call<Model_ValidatePhone> getValidatePhone(@Body Model_ValidatePhone userPhoneValidate);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("user/join")
    Call<Model_UserJoin> postUserJoin(@Body Model_UserJoin userJoin);

    @PATCH("user/join/detail/{userId}")
    Call<Model_UserId> patchUserId(@Path("userId") long userId, @Body Model_UserId userJoinInfo);

    //FCM
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @PATCH("push/{userId}")
    Call<Void> patchFcmToken(@Header("Authorization") String auth, @Query("fcmToken") String fcmToken);

    //profile
    @GET("/home/profile")
    Call<ModelProfileResult> getUserProfile(@Header("Authorization") String auth);

    @PATCH("user/join/detail/{userId}")
    Call<Model_UserNick> patchUserNick(@Path("userId") long userId, @Body Model_UserNick userNick);



}
