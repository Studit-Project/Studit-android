package com.example.studit.retrofit.join;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Model_UserId {

    @Expose
    @SerializedName("userId")
    private long userId;

    public Model_UserId(long userId) {
        this.userId = userId;
    }

    public long getUserId() { return userId; }

    public void setUserId(long userId) { this.userId= userId; }

}
