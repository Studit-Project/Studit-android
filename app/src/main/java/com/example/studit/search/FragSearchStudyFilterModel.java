package com.example.studit.search;

import java.io.Serializable;
import java.time.LocalDateTime;

public class FragSearchStudyFilterModel implements Serializable {
    private int id;
    private String title;
    private int userId;
    private String studyStatus;


    public FragSearchStudyFilterModel(int id, String title, int userId, String studyStatus) {
        this.id = id;
        this.title = title;
        this.userId = userId;
        this.studyStatus = studyStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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