package com.example.studit.retrofit.studyhome;

import com.example.studit.retrofit.search.ModelPostAll;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelStudyListAll {

    @SerializedName("code")
    private int code;

    @SerializedName("result")
    private List<ModelStudyList> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ModelStudyList> getResult() {
        return result;
    }

    public void setResult(List<ModelStudyList> result) {
        this.result = result;
    }
}
