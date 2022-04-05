package com.example.studit.main;

public class FragHomeStudyModel {
    String title;
    String info;
    String day;
    String contentsNum;

    public FragHomeStudyModel(String title, String info, String day, String contentsNum) {
        this.title = title;
        this.info = info;
        this.day = day;
        this.contentsNum = contentsNum;
    }

    public String getContentsNum() {
        return contentsNum;
    }

    public void setContentsNum(String contentNum) {
        this.contentsNum = contentNum;
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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
