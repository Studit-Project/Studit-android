package com.example.studit.retrofit.home;

import androidx.annotation.NonNull;

import com.example.studit.retrofit.search.ModelPostAll;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelHomeList {

    @SerializedName("nickname")
    private String nickname;

    @SerializedName("challenge")
    private ModelHomeChall challenge;

    @SerializedName("studies")
    private List<ModelHomeStudy> studies;

    public ModelHomeList(String nickname, ModelHomeChall challenge, List<ModelHomeStudy> studies) {
        this.nickname = nickname;
        this.challenge = challenge;
        this.studies = studies;
    }

    public ModelHomeChall getChallenge() {
        return challenge;
    }

    public void setChallenge(ModelHomeChall challenge) {
        this.challenge = challenge;
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

    @NonNull
    @Override
    public String toString() {
        return "[nickname: " +
                getNickname() +
                "studys: " +
                getStudies() +
                "]";
    }
}
