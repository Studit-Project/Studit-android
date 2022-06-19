package com.example.studit.retrofit.study;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelStudyDetail {
    @SerializedName("announcement")
    private String announcement;

    @SerializedName("followers")
    private List<ModelFollower> followers;

    @SerializedName("id")
    private int id;

    @SerializedName("introduction")
    private String introduction;

    @SerializedName("leader")
    private ModelLeader leader;

    @SerializedName("name")
    private String name;

    public ModelStudyDetail(String announcement, List<ModelFollower> followers, int id, String introduction, ModelLeader leader, String name) {
        this.announcement = announcement;
        this.followers = followers;
        this.id = id;
        this.introduction = introduction;
        this.leader = leader;
        this.name = name;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public List<ModelFollower> getFollowers() {
        return followers;
    }

    public void setFollowers(List<ModelFollower> followers) {
        this.followers = followers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public ModelLeader getLeader() {
        return leader;
    }

    public void setLeader(ModelLeader leader) {
        this.leader = leader;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
