package com.example.studit.retrofit.study.registerstudy;

import com.google.gson.annotations.SerializedName;

public class ModelRegisterStudy {

    @SerializedName("activity")
    private String activity;

//    @SerializedName("city")
//    private String city;
//
//    @SerializedName("district")
//    private String district;
//
//    @SerializedName("introduction")
//    private String introduction;

    @SerializedName("name")
    private String name;

//    @SerializedName("number")
//    private int number;
//
//    @SerializedName("province")
//    private String province;
//
//    @SerializedName("target")
//    private String target;
//
//    @SerializedName("code")
//    private int code;
//
//    @SerializedName("isSuccess")
//    private boolean isSuccess;
//
//    @SerializedName("message")
//    private String message;

//    @SerializedName("result")
//    private int result;

    public ModelRegisterStudy(String activity, String name) {
        this.activity = activity;
//        this.city = city;
//        this.district = district;
//        this.introduction = introduction;
        this.name = name;
//        this.number = number;
//        this.province = province;
//        this.target = target;
//        this.code = code;
//        this.isSuccess = isSuccess;
//        this.message = message;
//        this.result = result;
    }
    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getDistrict() {
//        return district;
//    }
//
//    public void setDistrict(String district) {
//        this.district = district;
//    }
//
//    public String getIntroduction() {
//        return introduction;
//    }
//
//    public void setIntroduction(String introduction) {
//        this.introduction = introduction;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public int getNumber() {
//        return number;
//    }
//
//    public void setNumber(int number) {
//        this.number = number;
//    }
//
//    public String getProvince() {
//        return province;
//    }
//
//    public void setProvince(String province) {
//        this.province = province;
//    }
//
//    public String getTarget() {
//        return target;
//    }
//
//    public void setTarget(String target) {
//        this.target = target;
//    }
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }

//    public boolean isSuccess() {
//        return isSuccess;
//    }
//
//    public void setSuccess(boolean success) {
//        isSuccess = success;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }

//    public int getResult() {
//        return result;
//    }
//
//    public void setResult(int result) {
//        this.result = result;
//    }

    @Override
    public java.lang.String toString() {
        return "ModelRegisterStudy{" +
                "activity=" + activity +
//                "city='" + city +
//                "district='" + district +
//                "introduction='" + introduction +
                "name='" + name +
//                "number=" + number +
//                "province=" + province +
//                "target=" + target +
                '}';
    }
}