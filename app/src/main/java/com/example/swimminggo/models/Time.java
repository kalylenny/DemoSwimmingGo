package com.example.swimminggo.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Time {
    private int sec, millisec, min, numOfWeek, quarter, month;

    public Time(JSONObject jsonObject) {
        try {
            this.sec = jsonObject.getInt("swim_sec");
            this.millisec = jsonObject.getInt("swim_millisec");
            this.min = jsonObject.getInt("swim_min");
            if (jsonObject.has("number_of_week"))
                this.numOfWeek = jsonObject.getInt("number_of_week");
            if (jsonObject.has("quarter"))
                this.quarter = jsonObject.getInt("quarter");
            if (jsonObject.has("month"))
                this.month = jsonObject.getInt("month");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Time(int min) {
        this.min = min;
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

    public int getQuarter() {
        return quarter;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int toMillisec() {
        return this.min * 1000 * 60 + this.sec * 1000 + millisec;
    }

    public float toSecond(){
        return this.min * 60 + this.sec + (float)this.millisec/ 1000;
    }
}
