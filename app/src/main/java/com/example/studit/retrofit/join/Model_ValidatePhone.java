package com.example.studit.retrofit.join;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

public class Model_ValidatePhone extends JSONObject {

    @Expose
    @SerializedName("phone")
    private String phone;

   // @SerializedName("num")
  //  private String num;

    @SerializedName("result")
    private JSONObject result;

    public Model_ValidatePhone(String phone, JSONObject result) {
        this.phone = phone;
        this.result = result;
    }

    public static String getPhone(String phone){ return phone;}

    public void setPhone(String email) { this.phone = phone; }

 //   public static String getNum(String num){ return num; }

  //  public void setNum(String num) { this.num = num; }

    public static JSONObject getResult(Model_ValidatePhone result){ return result; }

    public void setResult(JSONObject result) { this.result = result; }


    @Override
    public java.lang.String toString() {
        return "Model_ValidatePhone{" +
                "phone=" + phone +
        //        ", num=" + num +
                ", result=" + result +
                '}';
    }
}
