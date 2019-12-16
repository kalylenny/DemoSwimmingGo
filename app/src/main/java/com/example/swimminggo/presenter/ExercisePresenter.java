package com.example.swimminggo.presenter;

import com.example.swimminggo.models.Exercise;

public interface ExercisePresenter {
    void onGetPhases();
    void onGetDistances();
    void onGetStyles();
    void onCreateExercise(Exercise exercise);
    void onGetListExercise();
    void onDeleteExercise(int exerciseId);
    void onEditExercise(Exercise exercise);
}
