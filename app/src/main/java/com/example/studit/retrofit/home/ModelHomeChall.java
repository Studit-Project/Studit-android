package com.example.studit.retrofit.home;

import com.google.gson.annotations.SerializedName;

public class ModelHomeChall {
    @SerializedName("id")
    private int id;

    public ModelHomeChall(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
