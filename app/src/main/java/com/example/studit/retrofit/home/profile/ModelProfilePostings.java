package com.example.studit.retrofit.home.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelProfilePostings {

    @Expose
    @SerializedName("category")
    private String category;

    @Expose
    @SerializedName("content")
    private String content;

    @Expose
    @SerializedName("id")
    private int postId;

    public ModelProfilePostings(String category, String content, int postId) {
        this.category = category;
        this.content = content;
        this.postId = postId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) { this.category = category; }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

}
