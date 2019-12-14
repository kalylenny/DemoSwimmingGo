package com.example.swimminggo.singleton;

import com.example.swimminggo.models.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ListExercise {
    private static  ListExercise ourInstance;
    private List<Exercise> exercises;
    public static ListExercise getInstance() {
        return ourInstance;
    }

    public static void newInstance(){
        ourInstance = new ListExercise();
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    private ListExercise() {
        exercises = new ArrayList<>();
    }
    public void clearData() {
        ourInstance = null;
    }
}
