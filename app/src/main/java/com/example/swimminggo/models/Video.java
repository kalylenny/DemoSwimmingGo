package com.example.swimminggo.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Video {
    private String name, link;

    public Video(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public Video(JSONObject jsonObject){
        try {
            this.link = jsonObject.getString("link");
            this.name = jsonObject.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
