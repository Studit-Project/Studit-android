package com.example.studit.retrofit.studyhome;

import com.example.studit.retrofit.search.ModelPostAll;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelStudyListAll {
    @SerializedName("result")
    private List<ModelStudyList> studyLists;

    public ModelStudyListAll(List<ModelStudyList> studyLists) {
        this.studyLists = studyLists;
    }

    public List<ModelStudyList> getStudyLists() {
        return studyLists;
    }

    public void setStudyLists(List<ModelStudyList> studyLists) {
        this.studyLists = studyLists;
    }
}
