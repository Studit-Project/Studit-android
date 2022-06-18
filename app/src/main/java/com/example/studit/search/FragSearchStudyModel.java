package com.example.studit.search;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDateTime;

public class FragSearchStudyModel implements Serializable {
    private int id;
    private String title;
    private int userId;
    private LocalDateTime localDateTime;
    private String studyStatus;


//    public FragSearchStudyModel(int id, String title, int userId, LocalDateTime localDateTime, String studyStatus) {
//        this.id = id;
//        this.title = title;
//        this.userId = userId;
//        this.localDateTime = localDateTime;
//        this.studyStatus = studyStatus;
//    }

    public FragSearchStudyModel(int id, String title, int userId, String studyStatus) {
        this.id = id;
        this.title = title;
        this.userId = userId;
        this.localDateTime = null;
        this.studyStatus = studyStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getStudyStatus() {
        return studyStatus;
    }

    public void setStudyStatus(String studyStatus) {
        this.studyStatus = studyStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
