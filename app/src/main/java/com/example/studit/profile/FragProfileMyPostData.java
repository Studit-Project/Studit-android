package com.example.studit.profile;

public class FragProfileMyPostData {

    private String postData;
    private String postDate;

    public FragProfileMyPostData(String postData, String postDate){
        this.postData = postData;
        this.postDate = postDate;
    }

    public String getMyPostData(){
        return this.postData;
    }

    public String getMyPostDate(){
        return this.postDate;
    }
}
