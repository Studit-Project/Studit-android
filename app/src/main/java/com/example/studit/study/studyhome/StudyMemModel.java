package com.example.studit.study.studyhome;

public class StudyMemModel {
    String name;
    String leader;

    public StudyMemModel(String name, String leader) {
        this.name = name;
        this.leader = leader;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeader() {
        return this.leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

}
