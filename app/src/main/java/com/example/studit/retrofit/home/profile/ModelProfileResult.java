package com.example.studit.retrofit.home.profile;

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

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public ModelProfileUserList getResult() {
        return result;
    }

    public void setResult(ModelProfileUserList result) {
        this.result = result;
    }



}
