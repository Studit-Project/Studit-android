package com.example.studit.retrofit.posting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelPostCommentList {

    @Expose
    @SerializedName("content")
    private String content;

    @Expose
    @SerializedName("id")
    private long commentId;

    @Expose
    @SerializedName("localDateTime")
    private String commentDate;

    @Expose
    @SerializedName("userId")
    private long userId;

    @Expose
    @SerializedName("nickname")
    private String userNick;

    public ModelPostCommentList(String content, long commentId, String commentDate, String userId){
        this.content = content;
        this.commentId = commentId;
        this.commentDate = commentDate;
        this.userNick = userNick;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCommentId() { return commentId; }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public long getUserId() { return userId; }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserNick() { return userNick; }

    public void setUserNick(String userNick) { this.userNick = userNick; }
}
