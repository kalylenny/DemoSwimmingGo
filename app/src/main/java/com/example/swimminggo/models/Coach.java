package com.example.swimminggo.models;

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
        return null;
    }

    public Coach(){
        super();
    }
}
