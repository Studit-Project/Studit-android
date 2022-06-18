package com.example.studit.login;


import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class LoginRequest {

    @SerializedName("phone")
    public String phone;

    @SerializedName("password")
    public String password;

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginRequest(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }
}
