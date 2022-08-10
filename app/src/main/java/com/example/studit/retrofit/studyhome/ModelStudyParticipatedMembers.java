package com.example.studit.retrofit.studyhome;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelStudyParticipatedMembers {

    @Expose
    @SerializedName("email")
    private String email;

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("nickname")
    private String nickname;

    @Expose
    @SerializedName("userName")
    private String userName;

    public ModelStudyParticipatedMembers(String email, int id, String nickname, String userName) {
        this.email = email;
        this.id = id;
        this.nickname = nickname;
        this.userName = userName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
