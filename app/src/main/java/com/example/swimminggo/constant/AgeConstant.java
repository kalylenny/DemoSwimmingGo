package com.example.swimminggo.constant;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class AgeConstant {

    public List<Integer> listAge;

    public static AgeConstant ourInstance;

    public static AgeConstant getInstance(){
        if (ourInstance == null)
            ourInstance = new AgeConstant();
        return ourInstance;
    }

    public void clearData(){
        ourInstance = new AgeConstant();
    }

    public AgeConstant(){
        listAge = new ArrayList<>();
    }

    public void getDataListAge(JSONArray age){
        for(int i = 0; i < age.length(); i++){
            try {
                listAge.add(age.getJSONObject(i).getInt("age_num"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
