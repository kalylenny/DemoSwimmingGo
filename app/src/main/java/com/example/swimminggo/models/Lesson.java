package com.example.swimminggo.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Lesson implements Serializable {
    private int coachId, id;
    private String name;
    private List<Exercise> exercises;

    public Lesson(JSONObject jsonObject){
        try {
            this.coachId = jsonObject.getInt("coach_id");
            this.id = jsonObject.getInt("id");
            this.name = jsonObject.getString("name");
            JSONArray jsonArray = jsonObject.getJSONArray("exercises");
            this.exercises = new ArrayList<>();
            for(int i = 0; i < jsonArray.length(); i++){
                this.exercises.add(new Exercise(jsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
