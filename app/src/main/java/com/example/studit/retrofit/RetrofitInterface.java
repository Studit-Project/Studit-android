package com.example.studit.retrofit;

import com.example.studit.login.LoginRequest;
import com.example.studit.login.LoginResponse;
import com.example.studit.login.LoginResponseList;
import com.example.studit.retrofit.home.ModelHomeResult;
import com.example.studit.retrofit.home.profile.ModelProfileResult;
import com.example.studit.retrofit.home.profile.setting.Model_StatusMessage;
import com.example.studit.retrofit.home.profile.setting.Model_UserNick;
import com.example.studit.retrofit.join.ModelUserJoinInfo;
import com.example.studit.retrofit.join.Model_UserId;
import com.example.studit.retrofit.join.Model_UserJoin;
import com.example.studit.retrofit.join.Model_ValidatePhone;
import com.example.studit.retrofit.posting.ModelPostComment;
import com.example.studit.retrofit.posting.ModelPostCreate;
import com.example.studit.retrofit.posting.ModelPostDetail;
import com.example.studit.retrofit.search.ModelPostAllList;
import com.example.studit.retrofit.study.ModelStudyResult;
import com.example.studit.retrofit.study.registerstudy.ModelRegisterStudy;
import com.example.studit.retrofit.studyhome.ModelStudyListAll;
import com.example.studit.study.mystudy.MyStudySetModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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

    //postDetail
    @GET("posting/{postingId}")
    Call<ModelPostDetail> getPostDetail(@Header("Authorization") String auth, @Path("postingId") long postingId);

    @POST("posting/{postingId}/new-comment")
    Call<ModelPostComment> postPostComment(@Header("Authorization") String auth, @Path("postingId") long postingId, @Body ModelPostComment content);

    //study
    @GET("study/{studyId}")
    Call<ModelStudyResult> getStudyByStudyId(@Path("studyId") Long studyId, @Header("Authorization") String auth);

    @DELETE("study/{studyId}")
    Call<Void> deleteStudyByStudyId(@Path("studyId") Long studyId, @Header("Authorization") String auth);

    @PATCH("study/{studyId}/exit")
    Call<Void> deleteStudyExitByStudyId(@Path("studyId") Long studyId, @Header("Authorization") String auth);


    @PATCH("study/{studyId}")
    Call<Void> postNewStudyMemberByStudyId(@Path("studyId") Long studyId, @Body MyStudySetModel myStudySetModel, @Header("Authorization") String auth);

    @DELETE("study/{studyId}/expulsion/{followerId}")
    Call<Void> deleteStudyMemberByStudyId(@Path("studyId") Long studyId, @Path("followerId") Long followerId, @Header("Authorization") String auth);

    //studyhome
    @GET("study/management")
    Call<ModelStudyListAll> getData(@Header("Authorization") String auth);
//    @GET("study/management")
//    Call<ModelStudyList> getDataList(@Body ModelStudyList modelStudyList);

    //home
    @GET("home")
    Call<ModelHomeResult> getHomeList(@Header("Authorization") String auth);

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

    //fcm
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @PATCH("/push")
    Call<Void> patchFcmToken(@Header("Authorization") String auth, @Query("fcmToken") String fcmToken);

    //profile
    @GET("/home/profile")
    Call<ModelProfileResult> getUserProfile(@Header("Authorization") String auth);

    @PATCH("user/join/detail/{userId}")
    Call<Model_UserNick> patchUserNick(@Path("userId") long userId, @Body Model_UserNick userNick);
    Call<Model_UserId> patchUserId(@Path("userId") long userId, @Body ModelUserJoinInfo userJoinInfo);

    @PATCH("user/info/message")
    Call<Model_StatusMessage> patchStatusMessage(@Header("Authorization") String auth, @Body Model_StatusMessage userStatus);

    //register study
    @POST("study/management/new")
    Call<ModelRegisterStudy> postRegisterStudy(@Body ModelRegisterStudy modelRegisterStudy, @Header("Authorization") String auth);

    // post create
    @POST("posting/new")
    Call<ModelPostCreate> postPostCreate(@Body ModelPostCreate modelPostCreate, @Header("Authorization") String auth);

    @PATCH("user/join/detail/{userId}")
    Call<Model_StatusMessage> patchStatus(@Path("userId") long userId, @Body Model_StatusMessage statusMessage);



}
