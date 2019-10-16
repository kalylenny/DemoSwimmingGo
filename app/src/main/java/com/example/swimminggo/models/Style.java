package com.example.swimminggo.models;

import androidx.annotation.NonNull;

import org.json.JSONObject;

public class Style {
    private String id, value;

    public Style(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public Style(JSONObject jsonObject) {
        try {
            this.id = jsonObject.getString("id");
            this.value = jsonObject.getString("swim_name");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
