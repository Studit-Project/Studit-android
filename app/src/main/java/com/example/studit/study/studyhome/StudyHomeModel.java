package com.example.studit.study.studyhome;

public class StudyHomeModel {

    private String name;
    private String activity;
    private int id;
//    private int currentNum;

    public StudyHomeModel(String name, String activity, int id) {
        this.name = name;
        this.activity = activity;
        this.id = id;
//        this.currentNum = curentNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public int getCurrentNum() {
//        return currentNum;
//    }
//
//    public void setCurrentNum(int currentNum) {
//        this.currentNum = currentNum;
//    }
}
