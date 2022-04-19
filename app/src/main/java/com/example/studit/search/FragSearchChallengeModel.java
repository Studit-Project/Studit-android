package com.example.studit.search;

public class FragSearchChallengeModel {
    String state;
    String title;
    String tag;
    String progress;

    public FragSearchChallengeModel(String state, String title, String tag, String progress) {
        this.state = state;
        this.title = title;
        this.tag = tag;
        this.progress = progress;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}
