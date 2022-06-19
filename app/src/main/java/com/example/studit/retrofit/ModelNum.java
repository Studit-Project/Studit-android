package com.example.studit.retrofit;

import com.google.gson.annotations.SerializedName;

public class ModelNum {
    @SerializedName("num")
    private String nul;

    public ModelNum(String nul) {
        this.nul = nul;
    }

    public String getNul() {
        return nul;
    }

    public void setNul(String nul) {
        this.nul = nul;
    }
}
