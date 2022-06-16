package com.example.studit.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Model_PostAllList {
    @SerializedName("result")
    private List<Model_PostAll> posts;

    public Model_PostAllList(List<Model_PostAll> posts) {
        this.posts = posts;
    }

    public List<Model_PostAll> getPosts() {
        return posts;
    }

    public void setPosts(List<Model_PostAll> posts) {
        this.posts = posts;
    }
}
