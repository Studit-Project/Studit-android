package com.example.studit.study;

public class FragStudyModel {
    private String title;
    private String editor;

    public FragStudyModel(String title, String editor) {
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

    public void setEditor(String editor) { this.editor = editor; }
}
