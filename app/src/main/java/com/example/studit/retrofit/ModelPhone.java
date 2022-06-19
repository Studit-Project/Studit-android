package com.example.studit.retrofit;

import com.google.gson.annotations.SerializedName;

public class ModelPhone {
    @SerializedName("phone")
    private String phone;

    public ModelPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
