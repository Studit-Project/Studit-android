package com.example.studit.retrofit.home.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelProfilePostings {

    @Expose
    @SerializedName("field")
    private String field;

    @Expose
    @SerializedName("title")
    private String title;

    @Expose
    @SerializedName("content")
    private String content;

    @Expose
    @SerializedName("cratedAt")
    private String date;

    @Expose
    @SerializedName("id")
    private int postId;

    public ModelProfilePostings(String field,String title, String content,String date, int postId) {
        this.field = field;
        this.title = title;
        this.content = content;
        this.date = date;
        this.postId = postId;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) { this.field = field; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

}
