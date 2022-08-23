package com.example.studit.profile;

public class FragProfileBadgeModel {
    private String badgeImg;
    private String badgeTxt;

    public FragProfileBadgeModel(int badgeImg, String badgeTxt){
        this.badgeImg = badgeTxt;
        this.badgeTxt = badgeTxt;
    }

    public String getBadgeImg() {
        return badgeImg;
    }

    public void setBadgeImg(String badgeImg){
        this.badgeImg = badgeImg;
    }

    public String getBadgeTxt(){
        return badgeTxt;
    }

    public void setBadgeTxt(String badgeTxt){
        this.badgeTxt = badgeTxt;
    }


}
