package com.example.studit.login;

import com.example.studit.retrofit.PreferenceHelper;
import com.example.studit.retrofit.RetrofitInterface;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class LoginRequest {

//    //서버 URL 설정(php 파일 연동)
//    final static private String URL = "http://54.180.97.161:8081/user/login.php";
//    private Map<String, String> map;
//
//    public LoginRequest(String userID, String userPassword, Response.Listener<String> listener) {
//        super(Method.POST, URL, listener, null);
//
//        map = new HashMap<>();
//        map.put("ID", userID);
//        map.put("Password", userPassword);
//    }
//
//    @Override
//    protected Map<String, String> getParams() throws AuthFailureError {
//        return map;
//    }

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
