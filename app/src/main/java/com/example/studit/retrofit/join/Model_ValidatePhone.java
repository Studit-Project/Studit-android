package com.example.studit.retrofit.join;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.BufferedSource;

public class Model_ValidatePhone {

    @Expose
    @SerializedName("phone")
    private String phone;

    public Model_ValidatePhone(String phone) {
        this.phone = phone;
    }

    public String getPhone(){ return phone;}

    public void setPhone(String phone) { this.phone = phone; }

    //public String getNum(){ return num; }

    //public void setNum(String num) { this.num = num; }

    //public static JSONObject getResult(JSONObject result){ return result; }

    //public void setResult(JSONObject result) { this.result = result; }

/*
    @Override
    public java.lang.String toString() {
        return "Model_ValidatePhone{" +
                "phone=" + phone +
      //          ", num=" + num +
      //          ", result=" + result +
                '}';
    } */
}
