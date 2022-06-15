package com.example.studit.study.noticeboard;

import android.view.View;

public class NoticeBoardModel {

    private String title;
    private String editor;

    public NoticeBoardModel(String title, String editor) {
        this.title = title;
        this.editor = editor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }
}
