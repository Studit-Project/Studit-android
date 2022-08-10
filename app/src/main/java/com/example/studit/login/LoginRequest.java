package com.example.studit.login;


import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class LoginRequest {

    @SerializedName("identity")
    public String identity;

    @SerializedName("password")
    public String password;

    public String getIdentity() {
        return identity;
    }

    public String getPassword() {
        return password;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginRequest(String identity, String password) {
        this.identity = identity;
        this.password = password;
    }
}
