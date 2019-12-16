package com.example.swimminggo.singleton;

import android.os.Bundle;

import com.example.swimminggo.models.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ListExerciseByPhase {
    private static ListExerciseByPhase ourInstance;
    private List<Exercise> exercises;
    private List<Boolean> isCheckeds;
    public static ListExerciseByPhase getInstance() {
        if (ourInstance == null){
            ourInstance = new ListExerciseByPhase();
        }
        return ourInstance;
    }

    public static ListExerciseByPhase newInstance() {
        ourInstance = new ListExerciseByPhase();
        return ourInstance;
    }

    private ListExerciseByPhase() {
        exercises = new ArrayList<>();
        isCheckeds = new ArrayList<>();
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public List<Boolean> getIsCheckeds() {
        return isCheckeds;
    }
    public void clearData() {
        ourInstance = null;
    }
}
