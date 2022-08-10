package com.example.studit.login;

import com.google.gson.annotations.SerializedName;

public class LoginResponseList {

    @SerializedName("accessToken")
    private static String accessToken;

    public LoginResponseList(String accessToken) {
        this.accessToken = accessToken;
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
