package com.example.swimminggo.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Video {
    private String name, link;
    private int id;
    private String styleId;

    public Video(String name, String link, String styleId) {
        this.name = name;
        this.link = link;
        this.styleId = styleId;
    }

    public Video(JSONObject jsonObject){
        try {
            this.link = jsonObject.getString("link");
            this.name = jsonObject.getString("name");
            this.styleId = jsonObject.getString("style_id");
            this.id = jsonObject.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject toJSONObject(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("video_name", this.name);
            jsonObject.put("style_id", styleId);
            jsonObject.put("video_link", link);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
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

    public String getStyleId() {
        return styleId;
    }

    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }
}
