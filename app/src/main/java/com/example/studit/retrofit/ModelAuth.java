package com.example.studit.retrofit;

import com.google.gson.annotations.SerializedName;

public class ModelAuth {
    @SerializedName("accessToken")
    private String auth;

    public ModelAuth(String auth) {
        this.auth = auth;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
