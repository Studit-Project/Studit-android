package com.example.studit.retrofit.home.profile.setting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Model_StatusMessage {

    @Expose
    @SerializedName("isSuccess")
    private boolean isSuccess;

    @Expose
    @SerializedName("message")
    private List message;

    @Expose
    @SerializedName("statusMessage")
    private String statusMessage;


    public Model_StatusMessage(String statusMessage){
        this.statusMessage = statusMessage;
    }

    public String getStatusMessage() { return statusMessage; }

    public void setStatusMessage(String nickname) { this.statusMessage = statusMessage; }

    public boolean getIsSuccess() { return isSuccess; }

    public void setIsSuccess(boolean isSuccess) { this.isSuccess = isSuccess; }

    public List getMessage() { return message; }

    public void setMessage(List message) { this.message = message; }
}
