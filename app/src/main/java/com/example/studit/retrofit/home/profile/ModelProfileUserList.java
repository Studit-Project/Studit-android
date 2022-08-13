package com.example.studit.retrofit.home.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelProfileUserList {

    @SerializedName("id")
    private long userId;

    @Expose
    @SerializedName("identity")
    private String identity;

    @Expose
    @SerializedName("nickname")
    private String nickname;

    @Expose
    @SerializedName("statusMessage")
    private String statusMessage;

    @Expose
    @SerializedName("badges")
    private List<ModelProfileBadge> modelProfileBadge;

    @Expose
    @SerializedName("postings")
    private List<ModelProfilePostings> modelProfilePostings;

    public ModelProfileUserList(String nickname, String statusMessage, List<ModelProfileBadge> modelProfileBadge, List<ModelProfilePostings> modelProfilePostings) {
        this.nickname = nickname;
        this.statusMessage = statusMessage;
        this.modelProfileBadge = modelProfileBadge;
        this.modelProfilePostings = modelProfilePostings;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public List<ModelProfileBadge> getModelProfileBadge() {
        return modelProfileBadge;
    }

    public void setModelProfileBadge(List<ModelProfileBadge> modelProfileBadge) { this.modelProfileBadge = modelProfileBadge; }

    public List<ModelProfilePostings> getModelProfilePostings() {
        return modelProfilePostings;
    }

    public void setModelProfilePostings(List<ModelProfilePostings> modelProfilePostings) { this.modelProfilePostings = modelProfilePostings; }


}
