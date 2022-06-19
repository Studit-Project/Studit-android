package com.example.studit.retrofit.studyhome;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelStudyList {
    @SerializedName("activity")
    private String activity;

    @SerializedName("currentNum")
    private int currentNum;

    @SerializedName("id")
    private int id;

//    @SerializedName("leader")
//    private List<ModelStudyLeader> leader;

    @SerializedName("name")
    private String name;

    @SerializedName("number")
    private String number;
//
//    @SerializedName("participatedMembers")
//    private List<ModelStudyParticipatedMembers> participatedMembers;

    public ModelStudyList(String activity, int currentNum, int id, String name, String number ){
        this.activity = activity;
        this.currentNum = currentNum;
        this.id = id;
//        this.leader = leader;
        this.name = name;
        this.number = number;
//        this.participatedMembers = participatedMembers;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public int getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(int currentNum) {
        this.currentNum = currentNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public List<ModelStudyLeader> getLeader() {
//        return leader;
//    }
//
//    public void setLeader(List<ModelStudyLeader> leader) {
//        this.leader = leader;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

//    public List<ModelStudyParticipatedMembers> getParticipatedMembers() {
//        return participatedMembers;
//    }
//
//    public void setParticipatedMembers(List<ModelStudyParticipatedMembers> participatedMembers) {
//        this.participatedMembers = participatedMembers;
//    }
}
