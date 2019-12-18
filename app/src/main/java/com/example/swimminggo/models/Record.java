package com.example.swimminggo.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Record {
    private int swimmerId, coachId, exerciseId, millisec, sec, min;
    private String date;

    public Record(int swimmerId, int coachId, int exerciseId, String date){
        this.swimmerId = swimmerId;
        this.coachId = coachId;
        this.exerciseId = exerciseId;
        this.millisec = this.min = this.sec = 0;
        this.date = date;
    }

    public Record(JSONObject jsonObject){
        try{
            this.swimmerId = jsonObject.getInt("swimmer_id");
            this.coachId = jsonObject.getInt("coach_id");
            this.exerciseId = jsonObject.getInt("exercise_id");
            this.millisec = jsonObject.getInt("swim_millisec");
            this.sec = jsonObject.getInt("swim_sec");
            this.min = jsonObject.getInt("swim_min");
            this.date = jsonObject.getString("schedule");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public JSONObject toJSONObject(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("swimmer_id", this.swimmerId);
            jsonObject.put("coach_id", this.coachId);
            jsonObject.put("exercise_id", this.exerciseId);
            jsonObject.put("swim_millisec", this.millisec);
            jsonObject.put("swim_sec", this.sec);
            jsonObject.put("swim_min", this.min);
            jsonObject.put("schedule",this.date);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public int getSwimmerId() {
        return swimmerId;
    }

    public void setSwimmerId(int swimmerId) {
        this.swimmerId = swimmerId;
    }

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getMillisec() {
        return millisec;
    }

    public void setMillisec(int millisec) {
        this.millisec = millisec;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
