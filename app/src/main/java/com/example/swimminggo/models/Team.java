package com.example.swimminggo.models;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

public class Team implements Serializable {

    private int teamID;
    private int coachID;
    private String teamName;
    private int teamAge;
    private List<Integer> swimmerID;

    public Team(String teamName, int teamAge) {
        //this.teamID = teamID;
        this.teamName = teamName;
        this.teamAge = teamAge;
        //this.swimmerID = swimmerID;
    }

    public Team(int teamID, int coachID, String teamName, int teamAge) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.teamAge = teamAge;
        this.swimmerID = swimmerID;
    }

    public Team(JSONObject team){
        try {
            this.teamID = team.getInt("id");
            this.coachID = team.getInt("coach_id");
            this.teamAge = team.getInt("age_num");
            this.teamName = team.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    public int getTeamAge() {
        return teamAge;
    }

    public void setTeamAge(int teamAge) {
        this.teamAge = teamAge;
    }

    public List<Integer> getSwimmerID() {
        return swimmerID;
    }

    public void setSwimmerID(List<Integer> swimmerID) {
        this.swimmerID = swimmerID;
    }

    @NonNull
    @Override
    public String toString() {
        return teamName;
    }
}
