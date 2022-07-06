package com.example.studit.retrofit.join;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Model_ValidatePhone {

    @Expose
    @SerializedName("phone")
    private String phone;
    @Expose
    @SerializedName("code")
    private int code;
    @Expose
    @SerializedName("isSuccess")
    private Boolean isSuccess;
   // @Expose
   // @SerializedName("message")
   // private String message;
    @Expose
    @SerializedName("result")
    private String result;

    public Model_ValidatePhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public int getCode(){ return code; }

    public Boolean getIsSuccess(){ return isSuccess; }

   // public String getMessage(){ return message; }

    public String getResult(){ return result; }

    //public void setResult(String result) {  this.result = result; }


    @Override
    public java.lang.String toString() {
        return "Model_ValidatePhone{" +
                "code=" + code +
                ", isSuccess=" + isSuccess +
          //      ", message=" + message +
                ", result=" + result +
                '}';
    }
}
