package com.example.studit.retrofit.main;

import com.google.gson.annotations.SerializedName;

public class ModelMainFcm {
    @SerializedName("fcmToken")
    private String fcmToken;

    @SerializedName("userId")
    private Integer userId;

    public ModelMainFcm(String fcmToken, Integer userId) {
        this.fcmToken = fcmToken;
        this.userId = userId;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
