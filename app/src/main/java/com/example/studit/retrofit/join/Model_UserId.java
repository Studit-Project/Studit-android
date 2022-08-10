package com.example.studit.retrofit.join;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Model_UserId {

    @Expose
    @SerializedName("userId")
    private long userId;

    @Expose
    @SerializedName("birth")
    private String birth;
    @Expose
    @SerializedName("gender")
    private String gender;
    @Expose
    @SerializedName("nickname")
    private String nickname;

    public Model_UserId(int userId, String birth, String gender, String nickname) {
        this.userId = userId;
        this.birth = birth;
        this.gender = gender;
        this.nickname = nickname;
    }

    public long getUserId() { return userId; }

    public void setUserId(int userId) { this.userId= userId; }

    public String getBirth() { return birth; }

    public void setBirth(String birth) { this.birth = birth; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public String getNickname() { return nickname; }

    public void setNickname(String nickname) { this.nickname = nickname; }

    @Override
    public java.lang.String toString() {
        return "Model_UserJoin{" +
                "birth=" + birth + ", "+
                "gender=" + gender + ", "+
                "nickname=" + nickname +
                '}';
    }



}
