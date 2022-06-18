package com.example.studit.retrofit;

import com.google.gson.annotations.SerializedName;

public class Model_ValidatePhone {

    @SerializedName("phone")
    private String phone;

    @SerializedName("response.body()")
    private String numStr;

    public Model_ValidatePhone(String phone) {
        this.phone = phone;
    }

    public void setPhone(){
        this.phone = phone;
    }

    public String getPhone(){ return phone; }

    public void setNumStr(){
        this.numStr = numStr;
    }

    public static String getNumStr(String numStr){ return numStr; }

    @Override
    public java.lang.String toString() {
        return "Model_ValidatePhone{" +
                "phone='" + phone +
                "numStr='" + numStr +
                '}';
    }
}
