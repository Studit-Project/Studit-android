package com.example.studit.login;

import com.google.gson.annotations.SerializedName;

public class LoginResponseList {

    @SerializedName("accessToken")
    private String accessToken;

    public LoginResponseList(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
