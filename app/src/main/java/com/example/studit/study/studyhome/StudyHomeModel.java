package com.example.studit.study.studyhome;

public class StudyHomeModel {
    private String title;
    private String state;
    private int id;
    private int currentNum;

    public StudyHomeModel(String title, int id, String state, int curentNum) {
        this.title = title;
        this.id = id;
        this.state = state;
        this.currentNum = curentNum;

    }

    public int getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(int currentNum) {
        this.currentNum = currentNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) { this.state = state; }
}
