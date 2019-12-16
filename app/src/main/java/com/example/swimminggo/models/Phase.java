package com.example.swimminggo.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

public class Phase {
    private int id;
    private String value;

    public Phase(int id){
        this.id = id;
    }

    public Phase(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public Phase(JSONObject jsonObject){
        try {
            this.id = jsonObject.getInt("phase_id");
            this.value = jsonObject.getString("phase_name");
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @NonNull
    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        Phase phase = (Phase) obj;
        return this.id == phase.getId();
    }
}
