package com.example.studit.retrofit;

import com.google.gson.annotations.SerializedName;

public class model_test {
    @SerializedName("title")
    public String title;

    @SerializedName("body")
    public String body;


    @Override
    public String toString() {
        return "PostResult{" +
                "name=" + title +
                ", nickname=" + body + '\'' +
                '}';
    }
}
