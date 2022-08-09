package com.example.studit.retrofit.home;

import com.google.gson.annotations.SerializedName;

public class ModelHomeResult {
    @SerializedName("result")
    private ModelHomeList result;

    public ModelHomeResult(ModelHomeList result) {
        this.result = result;
    }

    public ModelHomeList getResult() {
        return result;
    }

    public void setResult(ModelHomeList result) {
        this.result = result;
    }
}
