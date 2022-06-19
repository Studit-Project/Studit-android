package com.example.studit.study.mystudy;

public class MyStudySetListModel {

    //    private String image;
    private String name;
    private String followerId;

    public MyStudySetListModel(String name) {
        this.name = name;
//        this.followerId = followerId;
    }

//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }


    public String getFollowerId() {
        return followerId;
    }

    public void setFollowerId(String followerId) {
        this.followerId = followerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
