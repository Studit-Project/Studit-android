package com.example.studit.profile;

public class FragProfilePostModel {

    private String field;
    private String title;
    private String content;
    private String date;

    public FragProfilePostModel(String field, String title, String content, String date){
        this.field = field;
        this.title = title;
        this.content = content;
        this.date = date;
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

}
