package com.example.swimminggo.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Note {
    int coachId, swimmerId, month, year, id;
    String note;

    public Note(int swimmerId, int month, int year, String note) {
        this.swimmerId = swimmerId;
        this.month = month;
        this.year = year;
        this.note = note;
    }

    public Note(JSONObject jsonObject){
        try {
            this.id = jsonObject.getInt("id");
            this.swimmerId = jsonObject.getInt("swimmer_id");
            this.month = jsonObject.getInt("month");
            this.coachId = jsonObject.getInt("coach_id");
            this.year = jsonObject.getInt("year");
            this.note = jsonObject.getString("note");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject toJSONObject(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("swimmer_id", this.swimmerId);
            jsonObject.put("note", this.note);
            jsonObject.put("month", this.month);
            jsonObject.put("year", this.year);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    public int getSwimmerId() {
        return swimmerId;
    }

    public void setSwimmerId(int swimmerId) {
        this.swimmerId = swimmerId;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
