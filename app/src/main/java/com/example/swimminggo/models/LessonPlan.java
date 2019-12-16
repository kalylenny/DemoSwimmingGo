package com.example.swimminggo.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class LessonPlan implements Serializable {
    private int id, userId, teamId, lessonId;
    private String lessonName, date, teamName;

    public LessonPlan(JSONObject jsonObject){
        try {
            this.id = jsonObject.getInt("id");
            this.userId = jsonObject.getInt("user_id");
            this.teamId = jsonObject.getInt("team_id");
            this.lessonName = jsonObject.getString("lesson_name");
            this.lessonId = jsonObject.getInt("lesson_id");
            this.date = jsonObject.getString("schedule");
            this.teamName = jsonObject.getString("team_name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public LessonPlan(){
        this.lessonName = "ASDASDAS";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
