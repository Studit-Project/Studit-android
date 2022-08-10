package com.example.studit.study.studyhome;

public class StudyHomeModel {

    private String title;
    private String activity;
    private int id;
    private int currentNum;

    public StudyHomeModel(String title, int id, String activity, int curentNum) {
        this.title = title;
        this.id = id;
        this.activity = activity;
        this.currentNum = curentNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(int currentNum) {
        this.currentNum = currentNum;
    }
}
