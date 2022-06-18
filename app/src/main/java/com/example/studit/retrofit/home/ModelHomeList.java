package com.example.studit.retrofit.home;

import com.example.studit.retrofit.search.ModelPostAll;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelHomeList {

    @SerializedName("nickname")
    private String nickname;

    @SerializedName("challenge")
    private int challenge;

    @SerializedName("studies")
    private List<ModelHomeStudy> studies;

    public ModelHomeList(String nickname, int challenge, List<ModelHomeStudy> studies) {
        this.nickname = nickname;
        this.studies = studies;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<ModelHomeStudy> getStudies() {
        return studies;
    }

    public void setStudies(List<ModelHomeStudy> studies) {
        this.studies = studies;
    }
}
