package com.example.swimminggo.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Distance {
    private int id, value;

    public Distance(int value){
        this.id = this.value = value;
    }

    public Distance(int id, int value){
        this.id = id;
        this.value = value;
    }

    public Distance(JSONObject jsonObject){
        try {
            this.value = jsonObject.getInt("distance_num");
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value + "";
    }
}
