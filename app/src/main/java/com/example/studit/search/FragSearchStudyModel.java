package com.example.studit.search;

public class FragSearchStudyModel {
    String state;
    String title;
    String tag;

    public FragSearchStudyModel(String state, String title, String tag) {
        this.state = state;
        this.title = title;
        this.tag = tag;
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
}
