package com.example.swimminggo.constant;

import com.example.swimminggo.models.Distance;
import com.example.swimminggo.models.Phase;
import com.example.swimminggo.models.Style;

import java.util.ArrayList;
import java.util.List;

public class ExerciseConstant {
    private static ExerciseConstant ourInstance;
    private List<Phase> phases;
    private List<Style> styles;
    private List<Distance> distances;
    private List<Integer> reps;

    public static ExerciseConstant getInstance() {
        return ourInstance;
    }

    public static void newInstance(){
        ourInstance = new ExerciseConstant();
    }

    private ExerciseConstant() {
        phases = new ArrayList<>();
        styles = new ArrayList<>();
        distances = new ArrayList<>();
        reps = new ArrayList<>();
        for(int i = 1; i <= 10; i++){
            reps.add(i);
        }
    }

    public List<Phase> getPhases() {
        return phases;
    }

    public List<Style> getStyles() {
        return styles;
    }

    public List<Distance> getDistances() {
        return distances;
    }

    public List<Integer> getReps(){
        return reps;
    }

    public void clearData() {
        ourInstance = null;
    }
}
