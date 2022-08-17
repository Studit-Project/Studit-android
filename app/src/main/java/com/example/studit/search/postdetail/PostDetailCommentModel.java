package com.example.studit.search.postdetail;

public class PostDetailCommentModel {
    private String userNick;
    private String userLevel;
    private String date;
    private String content;

    public PostDetailCommentModel(String userNick, String date, String content){
        this.userNick = userNick;
        this.date = date;
        this.content = content;
    }

    public String getUserNick() { return userNick; }

    public void setUserNick(String userNick) { this.userNick = userNick; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }
}
