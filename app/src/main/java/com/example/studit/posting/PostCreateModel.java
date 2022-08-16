package com.example.studit.posting;

import android.widget.SpinnerAdapter;

public class PostCreateModel {

    private String name;
    private String category;
    private String content;
    private String province;
    private String activity;
    private String target;
    private String gender;

    public PostCreateModel(String name, String category, String content, String province, String activity, String target, String gender) {
        this.name = name;
        this.category = category;
        this.content = content;
        this.province = province;
        this.activity = activity;
        this.target = target;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
