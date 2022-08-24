package com.example.studit.retrofit.posting;

import com.google.gson.annotations.SerializedName;

public class ModelPostCreate {

    @SerializedName("activity")
    private String activity;

    @SerializedName("content")
    private String content;

    @SerializedName("title")
    private String title;

    @SerializedName("target")
    private String target;

    @SerializedName("province")
    private String province;

    @SerializedName("category")
    private String category;

    @SerializedName("gender")
    private String gender;

    @SerializedName("field")
    private String field;

    @SerializedName("number")
    private int number;

    public ModelPostCreate(String activity, String content, String title, String target, String province, String gender, String field) {
        this.activity = activity;
        this.content = content;
        this.title = title;
        this.target = target;
        this.province = province;
//        this.category = category;
        this.gender = gender;
        this.field = field;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
