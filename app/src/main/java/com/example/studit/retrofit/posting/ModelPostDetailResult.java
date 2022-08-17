package com.example.studit.retrofit.posting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelPostDetailResult {

    @Expose
    @SerializedName("category")
    private String category;

    @Expose
    @SerializedName("commentList")
    private List<ModelPostCommentList> commentList;

    @Expose
    @SerializedName("content")
    private String content;

    @Expose
    @SerializedName("id")
    private long id;

    @Expose
    @SerializedName("localDateTime")
    private String localDateTime;

    @Expose
    @SerializedName("title")
    private String title;

    @Expose
    @SerializedName("province")
    private String province;

    @Expose
    @SerializedName("gender")
    private String gender;

    @Expose
    @SerializedName("target")
    private String target;

    @Expose
    @SerializedName("activity")
    private String activity;

    @Expose
    @SerializedName("userInfoDto")
    private ModelUserInfo userInfo;


    public ModelPostDetailResult(
            String category,String activity, List<ModelPostCommentList> commentList, String content, long id, String localDateTime,
            String title, String province, String gender, String target, ModelUserInfo userInfo) {
        this.category = category;
        this.commentList = commentList;
        this.id = id;
        this.localDateTime = localDateTime;
        this.title = title;
        this.content = content;
        this.userInfo = userInfo;
        this.province = province;
        this.gender = gender;
        this.target = target;
        this.activity = activity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<ModelPostCommentList> getCommentList() { return commentList; }

    public void setCommentList(List<ModelPostCommentList> commentList) { this.commentList = commentList; }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(String localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getProvince() { return province; }

    public void setProvince(String province) { this.province = province; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public String getTarget() { return target; }

    public void setTarget(String target) { this.target = target; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActivity() { return activity; }

    public void setActivity(String activity) { this.activity = activity; }

    public ModelUserInfo getUserInfo() { return userInfo; }

    public void setUserInfo(ModelUserInfo userInfo) { this.userInfo = userInfo; }
}
