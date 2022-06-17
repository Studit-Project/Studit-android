package com.example.studit.retrofit;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class Model_PostAll {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("userId")
    private int userId;

    @SerializedName("localDateTime")
    private LocalDateTime localDateTime;

    @SerializedName("studyStatus")
    private String studyStatus;


    public Model_PostAll(int id, String title, int userId, LocalDateTime localDateTime, String studyStatus) {
        this.id = id;
        this.title = title;
        this.userId = userId;
        this.localDateTime = localDateTime;
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
