package com.example.studit.home;

public class FragHomeStudyModel {
    String title;
    String info;
   // String day;

    public FragHomeStudyModel(String title, String info) {
        this.title = title;
        this.info = info;
        //this.day = day;
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
