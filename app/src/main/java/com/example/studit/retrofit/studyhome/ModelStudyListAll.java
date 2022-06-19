package com.example.studit.retrofit.studyhome;

import com.example.studit.retrofit.search.ModelPostAll;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelStudyListAll {
    @SerializedName("result")
    private List<ModelPostAll> posts;

    public ModelStudyListAll(List<ModelPostAll> posts) {
        this.posts = posts;
    }

    public List<ModelPostAll> getPosts() {
        return posts;
    }

    public void setPosts(List<ModelPostAll> posts) {
        this.posts = posts;
    }
}
