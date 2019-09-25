package com.example.swimminggo.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Coach extends User {

    public Coach(String username, String dob, String email, String firstName, String lastName, String phone, int gender, int id, String roleName, int isVerified) {
        super(username, dob, email, firstName, lastName, phone, gender, id, roleName, isVerified);
    }

    @Override
    public void getDataFromJSONObject(JSONObject jsonObject) {
        try {
            super.setUser(
                    jsonObject.getString("dob"),
                    jsonObject.getString("email"),
                    jsonObject.getString("first_name"),
                    jsonObject.getString("last_name"),
                    jsonObject.getString("phone"),
                    jsonObject.getInt("gender"),
                    jsonObject.getInt("id"),
                    jsonObject.getString("role_name"),
                    jsonObject.getInt("is_verified")
            );
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public JSONObject convertToJSONObject() {
        JSONObject coachObject = new JSONObject();
        try {
            coachObject.put("username", super.getUsername());
            coachObject.put("first_name", super.getFirstName());
            coachObject.put("last_name", super.getLastName());
            coachObject.put("phone", super.getPhone());
            coachObject.put("email",super.getEmail());
            coachObject.put("dob", super.getDob());
            coachObject.put("gender", super.getGender());
            coachObject.put("avatar","null");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return coachObject;
    }

    public Coach(){
        super();
    }
}
