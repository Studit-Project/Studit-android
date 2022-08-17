package com.example.studit.retrofit.home.profile.setting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Model_UserNick {

    @Expose
    @SerializedName("isSuccess")
    private boolean isSuccess;

    @Expose
    @SerializedName("message")
    private List message;

    @Expose
    @SerializedName("nickname")
    private String nickname;

    public Model_UserNick(String nickname){
        this.nickname = nickname;
    }

    public String getNickname() { return nickname; }

    public void setNickname(String nickname) { this.nickname = nickname; }

    public boolean getIsSuccess() { return isSuccess; }

    public void setIsSuccess(boolean isSuccess) { this.isSuccess = isSuccess; }

    public List getMessage() { return message; }

    public void setMessage(List message) { this.message = message; }

}
