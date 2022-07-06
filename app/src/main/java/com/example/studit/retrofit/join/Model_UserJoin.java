package com.example.studit.retrofit.join;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Model_UserJoin {

    @Expose
    @SerializedName("email")
    private String email;
    @Expose
    @SerializedName("password")
    private String password;
    @Expose
    @SerializedName("phone")
    private String phone;
    @Expose
    @SerializedName("userName")
    private String userName;

    public Model_UserJoin(String email, String password, String phone, String userName) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.userName = userName;
    }

    public String getEmail() { return email; }

    public void setSuccess(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.email = password; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    @Override
    public java.lang.String toString() {
        return "Model_UserJoin{" +
                "email='" + email +
                "password='" + password +
                "phone='" + phone +
                "userName='" + userName +
                '}';
    }
}

