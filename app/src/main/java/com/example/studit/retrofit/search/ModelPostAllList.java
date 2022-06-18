package com.example.studit.retrofit.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelPostAllList {
    @SerializedName("result")
    private List<ModelPostAll> posts;

    public ModelPostAllList(List<ModelPostAll> posts) {
        this.posts = posts;
    }

    public List<ModelPostAll> getPosts() {
        return posts;
    }

    public void setPosts(List<ModelPostAll> posts) {
        this.posts = posts;
    }
}
