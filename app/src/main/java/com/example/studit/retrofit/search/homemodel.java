package com.example.studit.retrofit.search;

import com.google.gson.annotations.SerializedName;

public class homemodel {
    @SerializedName("nickname")
    private String nickname;

    public homemodel(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
