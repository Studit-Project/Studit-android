package com.example.studit.retrofit.posting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelPostDetail {
    @Expose
    @SerializedName("isSuccess")
    private boolean isSuccess;

    @Expose
    @SerializedName("result")
    private ModelPostDetailResult result;

    @Expose
    @SerializedName("Authorization")
    private String auth;

    public ModelPostDetail(String auth) {
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

    public ModelPostDetailResult getResult() {
        return result;
    }

    public void setResult(ModelPostDetailResult result) {
        this.result = result;
    }
}
