package com.example.swimminggo.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Account {
    private String username;
    private String password;

    public Account(JSONObject account){
        try {
            this.username = account.getString("username");
            this.password = account.getString("password");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
