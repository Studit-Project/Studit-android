package com.example.studit.retrofit.posting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelUserInfo {

    @Expose
    @SerializedName("id")
    private long userId;

    @Expose
    @SerializedName("nickname")
    private String nickname;

    public ModelUserInfo (long userId, String nickname){
        this.userId = userId;
        this.nickname = nickname;
    }

    public long getUserId() { return userId; }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
