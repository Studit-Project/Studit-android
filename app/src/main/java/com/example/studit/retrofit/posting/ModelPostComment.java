package com.example.studit.retrofit.posting;

import com.google.gson.annotations.SerializedName;

public class ModelPostComment {

    @SerializedName("content")
    private String comment;

    public ModelPostComment(String comment){
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
