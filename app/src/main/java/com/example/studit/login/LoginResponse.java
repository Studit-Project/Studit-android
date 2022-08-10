package com.example.studit.login;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("code")
    public String code;

    @SerializedName("result")
    public LoginResponseList result;

    public LoginResponse(LoginResponseList result) { this.result = result; }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LoginResponseList getResult() {
        return result;
    }

    public void setResult(LoginResponseList result) {
        this.result = result;
    }
}
