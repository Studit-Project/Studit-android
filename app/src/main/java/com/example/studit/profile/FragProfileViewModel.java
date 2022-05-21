package com.example.studit.profile;

public class FragProfileViewModel {
    private String badgeImg;
    private String badgeTxt;

    public FragProfileViewModel(String badgeImg, String badgeTxt){
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
