package com.example.studit.search;

public class MyStudyActivityModel {
    String state;
    String title;
    String tag;
    String progress;

    public MyStudyActivityModel(String state, String title, String tag, String time) {
        this.state = state;
        this.title = title;
        this.tag = tag;
        this.progress = time;
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

    public String getProgress() { return progress; }

    public void setProgress(String progress) { this.progress = progress; }
}
