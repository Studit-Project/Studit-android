package com.example.studit.retrofit.home;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class ModelHomeStudy {
    @SerializedName("id")
    private int id;

    @SerializedName("introduction")
    private String intro;

    @SerializedName("name")
    private String name;

//    @SerializedName("createAt")
//    private LocalDateTime localDateTime;

    public ModelHomeStudy(int id, String intro, String name) {
        this.id = id;
        this.intro = intro;
        this.name = name;
        //this.localDateTime = localDateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public LocalDateTime getLocalDateTime() {
//        return localDateTime;
//    }
//
//    public void setLocalDateTime(LocalDateTime localDateTime) {
//        this.localDateTime = localDateTime;
//    }
}
