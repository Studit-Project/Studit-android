package com.example.studit.retrofit.posting;

import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.annotations.SerializedName;

import java.lang.annotation.Target;

public class ModelPostCreate {

    @SerializedName("activity")
    private String activity;

    @SerializedName("introduction")
    private String introduction;

    @SerializedName("name")
    private String name;

    @SerializedName("target")
    private String target;

    @SerializedName("province")
    private String province;

    @SerializedName("category")
    private String category;

    @SerializedName("gender")
    private String gender;

    public ModelPostCreate(String activity, String introduction, String name, String target, String province, String category, String gender) {
        this.activity = activity;
        this.introduction = introduction;
        this.name = name;
        this.target = target;
        this.province = province;
        this.category = category;
        this.gender = gender;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
