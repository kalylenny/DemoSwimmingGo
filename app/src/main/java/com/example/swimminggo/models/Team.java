package com.example.swimminggo.models;

import java.util.List;

public class Team {

    private int teamID;
    private String teamName;
    private String teamAge;
    private List<Integer> swimmerID;

    public Team(String teamName, String teamAge) {
        //this.teamID = teamID;
        this.teamName = teamName;
        this.teamAge = teamAge;
        //this.swimmerID = swimmerID;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamAge() {
        return teamAge;
    }

    public void setTeamAge(String teamAge) {
        this.teamAge = teamAge;
    }

    public List<Integer> getSwimmerID() {
        return swimmerID;
    }

    public void setSwimmerID(List<Integer> swimmerID) {
        this.swimmerID = swimmerID;
    }
}
