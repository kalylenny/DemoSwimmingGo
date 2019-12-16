package com.example.swimminggo.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Exercise implements Serializable, Parcelable {
    private int id;
    private String styleId;
    private int distance;
    private int rep;
    private int phaseId;

    public Exercise(String styleId, int distance, int rep, int phaseId) {
        this.styleId = styleId;
        this.distance = distance;
        this.rep = rep;
        this.phaseId = phaseId;
    }
    public Exercise(int id, String styleId, int distance, int rep, int phaseId) {
        this.id = id;
        this.styleId = styleId;
        this.distance = distance;
        this.rep = rep;
        this.phaseId = phaseId;
    }

    public Exercise(JSONObject jsonObject){
        try {
            this.id = jsonObject.getInt("id");
            this.styleId = jsonObject.getString("style_id");
            this.distance = jsonObject.getInt("distance_num");
            this.rep = jsonObject.getInt("repetition");
            this.phaseId = jsonObject.getInt("phase_id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected Exercise(Parcel in) {
        id = in.readInt();
        styleId = in.readString();
        distance = in.readInt();
        rep = in.readInt();
        phaseId = in.readInt();
    }

    public static final Creator<Exercise> CREATOR = new Creator<Exercise>() {
        @Override
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        @Override
        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };

    public JSONObject toJSONObject(){
        JSONObject result = new JSONObject();
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("style_id", this.styleId);
            jsonObject.put("distance_num", this.distance);
            jsonObject.put("repetition", this.rep);
            jsonObject.put("phase_id", this.phaseId);
            jsonObject.put("name","");
            if (this.id != 0){
                jsonObject.put("id", this.id);
            }
            result.put("exercise", jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStyleId() {
        return styleId;
    }

    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getRep() {
        return rep;
    }

    public void setRep(int rep) {
        this.rep = rep;
    }

    public int getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(int phaseId) {
        this.phaseId = phaseId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(styleId);
        dest.writeInt(distance);
        dest.writeInt(rep);
        dest.writeInt(phaseId);
    }
}
