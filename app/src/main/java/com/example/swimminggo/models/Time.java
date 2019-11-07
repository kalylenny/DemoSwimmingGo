package com.example.swimminggo.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Time {
    private int sec, millisec, min, numOfWeek;

    public Time(JSONObject jsonObject) {
        try {
            this.sec = jsonObject.getInt("swim_sec");
            this.millisec = jsonObject.getInt("swim_millisec");
            this.min = jsonObject.getInt("swim_min");
            this.numOfWeek = jsonObject.getInt("number_of_week");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public int getMillisec() {
        return millisec;
    }

    public void setMillisec(int millisec) {
        this.millisec = millisec;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getNumOfWeek() {
        return numOfWeek;
    }

    public void setNumOfWeek(int numOfWeek) {
        this.numOfWeek = numOfWeek;
    }

    public int toMillisec() {
        return this.min * 1000 * 60 + this.sec * 1000 + millisec;
    }
}
