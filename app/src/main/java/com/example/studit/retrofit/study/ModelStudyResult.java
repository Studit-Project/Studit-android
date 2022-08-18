package com.example.studit.retrofit.study;

import com.google.gson.annotations.SerializedName;

public class ModelStudyResult {
    @SerializedName("result")
    private ModelStudyDetail result;

    public ModelStudyResult(ModelStudyDetail result) {
        this.result = result;
    }

    public ModelStudyDetail getResult() {
        return result;
    }
}
