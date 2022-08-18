package com.example.studit.study.mystudy;

import com.google.gson.annotations.SerializedName;

public class MyStudyActivityGridModel {

    private String email;
    private int id;
    private String nickname;
    private String username;
    private int study_id;

    public MyStudyActivityGridModel(String email, int id, String nickname, String username, int study_id) {
        this.email = email;
        this.id = id;
        this.nickname = nickname;
        this.username = username;
        this.study_id = study_id;
    }

    public int getStudy_id() {
        return study_id;
    }

    public void setStudy_id(int study_id) {
        this.study_id = study_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
