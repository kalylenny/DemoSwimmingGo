package com.example.swimminggo.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Lesson {

    private int id, coachId;
    private String name;

    public Lesson(int id, int coachId, String name) {
        this.id = id;
        this.coachId = coachId;
        this.name = name;
    }

    public Lesson(JSONObject jsonObject){
        try {
            this.id = jsonObject.getInt("id");
            this.coachId = jsonObject.getInt("coach_id");
            this.name = jsonObject.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
