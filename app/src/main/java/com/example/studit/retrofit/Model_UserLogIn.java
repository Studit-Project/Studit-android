package com.example.studit.retrofit;

import com.google.gson.annotations.SerializedName;

public class Model_UserLogIn {

    @SerializedName("password")
    private String password;

    @SerializedName("phone")
    private String phone;


    public Model_UserLogIn(String password, String phone) {
        this.password = password;
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
