package com.example.studit.retrofit.join;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelUserJoinInfo {

    @Expose
    @SerializedName("birth")
    private String birth;
    @Expose
    @SerializedName("gender")
    private String gender;
    @Expose
    @SerializedName("nickname")
    private String nickname;

    public ModelUserJoinInfo(String birth, String gender, String nickname) {
        this.birth = birth;
        this.gender = gender;
        this.nickname = nickname;
    }

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
