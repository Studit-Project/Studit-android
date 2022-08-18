package com.example.studit.study.mystudy;

public class MyStudySetListModel {

    //    private String image;
    private int followerId;
    private String name;


    public MyStudySetListModel(int followerId, String name) {
        this.followerId = followerId;
        this.name = name;
    }

//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }


    public int getFollowerId() {
        return followerId;
    }

    public void setFollowerId(int followerId) {
        this.followerId = followerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
