package com.example.studit.retrofit;

import com.google.gson.annotations.SerializedName;

public class Model_ValidatePhone {

    @SerializedName("phone")
    private String phone;
    @SerializedName("numStr")
    private String numStr;

    public Model_ValidatePhone(String phone, String numStr) {
        this.phone = phone;
        this.numStr = numStr;
    }

    public void setPhone(){
        this.phone = phone;
    }

    public String getPhone(){
        return phone;
    }

    public void setNumStr(){
        this.numStr = numStr;
    }

    public String getNumStr(){
        return numStr;
    }

    @Override
    public java.lang.String toString() {
        return "Model_ValidatePhone{" +
                "phone='" + phone +
                ", numStr='" + numStr +
                '}';
    }
}
