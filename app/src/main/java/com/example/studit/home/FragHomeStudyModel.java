package com.example.studit.home;

import com.example.studit.retrofit.home.ModelHomeStudy;

public class FragHomeStudyModel {
    int  id;
    String title;
    String info;


    public FragHomeStudyModel(int id, String title, String info) {
        this.id = id;
        this.title = title;
        this.info = info;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
//
//    public String getDay() {
//        return day;
//    }
//
//    public void setDay(String day) {
//        this.day = day;
//    }
}
