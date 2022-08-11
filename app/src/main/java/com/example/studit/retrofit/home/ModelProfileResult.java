package com.example.studit.retrofit.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelProfileResult {

    @Expose
    @SerializedName("isSuccess")
    private boolean isSuccess;

//    @Expose
//    @SerializedName("result")
//    private ModelProfileUserList result;

    @Expose
    @SerializedName("Authorization")
    private String auth;

    public ModelProfileResult(String auth) {
        this.auth = auth;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

}
