package com.example.swimminggo.singleton;

import android.util.Log;

import com.example.swimminggo.models.Coach;
import com.example.swimminggo.models.Swimmer;
import com.example.swimminggo.models.User;

import org.json.JSONException;
import org.json.JSONObject;

public class UserProfile {
    public  User currentUser;
    public  String accessToken;
    private static UserProfile ourInstance;

    public static UserProfile getInstance() {
        if (ourInstance == null) {
            ourInstance = new UserProfile();
        }
        return ourInstance;
    }

    public void clearData(){
        ourInstance = null;
    }

    public void getUserProfileByJSONObject(JSONObject jsonObject) {
        try {
            if (jsonObject.getString("role_name").equals("swimmer")) {
                Swimmer swimmer = new Swimmer();
                swimmer.getDataFromJSONObject(jsonObject);
                this.currentUser = swimmer;
            } else {
                Coach coach = new Coach();
                coach.getDataFromJSONObject(jsonObject);
                this.currentUser = coach;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setAccessToken(String accessToken){
        this.accessToken = accessToken;
    }

    private UserProfile() {

    }
}
