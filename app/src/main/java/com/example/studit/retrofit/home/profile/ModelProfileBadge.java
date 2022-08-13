package com.example.studit.retrofit.home.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelProfileBadge {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("name")
    private String name;

    public int getBadgeId() {
        return id;
    }

    public void setBadgeId(int id) { this.id = id; }

    public String getBadgeName() {
        return name;
    }

    public void setBadgeName(String name) { this.name = name; }
}
