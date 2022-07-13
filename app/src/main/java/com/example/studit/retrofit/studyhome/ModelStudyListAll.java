package com.example.studit.retrofit.studyhome;

import com.example.studit.retrofit.search.ModelPostAll;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelStudyListAll {
    @SerializedName("result")
    public int result;

    @SerializedName("body")
    public List<ModelStudyList> body;

    @Override
    public String toString() {
        return "ModelStudyListAll{" +
                "body=" + body +
                '}';
     }
//    public ModelStudyListAll(List<ModelStudyList> studyLists) {
//        this.studyLists = studyLists;
//    }
//
//    public List<ModelStudyList> getStudyLists() {
//        return studyLists;
//    }
//
//    public void setStudyLists(List<ModelStudyList> studyLists) {
//        this.studyLists = studyLists;
//    }
}
