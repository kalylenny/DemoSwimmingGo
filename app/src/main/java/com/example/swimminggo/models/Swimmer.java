package com.example.swimminggo.models;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Swimmer extends User implements Serializable {
    private String parent_name, parent_phone;
    private float weight, height;

    public Swimmer(){
        super();
    }
    public Swimmer(String username, String dob, String email, String firstName, String lastName, String phone, int gender, int id, String roleName, float height, float weight, String parentName, String parentPhone, int isVerified) {
        super(username, dob, email, firstName, lastName, phone, gender, id, roleName, isVerified);
        this.weight = weight;
        this.height = height;
        this.parent_name = parentName;
        this.parent_phone = parentPhone;
    }

    public Swimmer(JSONObject swimmerObject){
        super(swimmerObject);
    }

    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    public String getParent_phone() {
        return parent_phone;
    }

    public void setParent_phone(String parent_phone) {
        this.parent_phone = parent_phone;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
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
            setHeight((float)jsonObject.getDouble("height"));
            setWeight((float)jsonObject.getDouble("weight"));
            setParent_name(jsonObject.getString("parent_name"));
            setParent_phone(jsonObject.getString("parent_phone"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JSONObject convertToJSONObject() {
        JSONObject swimmerObject = new JSONObject();
        try {
            swimmerObject.put("username", super.getUsername());
            swimmerObject.put("first_name", super.getFirstName());
            swimmerObject.put("last_name", super.getLastName());
            swimmerObject.put("phone", super.getPhone());
            swimmerObject.put("email",super.getEmail());
            swimmerObject.put("dob", super.getDob());
            swimmerObject.put("gender", super.getGender());
            swimmerObject.put("avatar", "kkkk");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return swimmerObject;
    }

    public void getSwimmerFromJSONObject(JSONObject jsonObject){
        try{
            super.setDob(jsonObject.getString("dob"));
            super.setFirstName(jsonObject.getString("first_name"));
            super.setLastName(jsonObject.getString("last_name"));
            super.setId(jsonObject.getInt("user_id"));
            super.setUsername(jsonObject.getString("username"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}