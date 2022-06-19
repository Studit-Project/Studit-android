package com.example.studit.retrofit.study;

import com.google.gson.annotations.SerializedName;

public class ModelLeader {
    @SerializedName("email")
    private String email;

    @SerializedName("id")
    private int id;

    @SerializedName("nickname")
    private String nickname;

    @SerializedName("username")
    private String username;

    @SerializedName("phone")
    private String phone;

    public ModelLeader(String email, int id, String nickname, String username, String phone) {
        this.email = email;
        this.id = id;
        this.nickname = nickname;
        this.username = username;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
