package com.example.swimminggo.presenter;

import com.example.swimminggo.models.Exercise;

import java.util.List;

public interface LessonPresenter {

    void onGetListExerciseByPhaseId(int phaseId, List<Exercise> exerciseList);
    void onCreateLesson(List<Exercise> exercises, String name);
}
