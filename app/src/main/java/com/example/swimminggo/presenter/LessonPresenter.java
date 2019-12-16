package com.example.swimminggo.presenter;

import com.example.swimminggo.models.Exercise;
import com.example.swimminggo.models.Lesson;

import java.util.List;

public interface LessonPresenter {

    void onGetListExerciseByPhaseId(int phaseId, List<Exercise> exerciseList);
    void onGetListExerciseByPhaseId(int phaseId, int lessonId);
    void onCreateLesson(List<Exercise> exercises, String name);
    void onGetListLesson();
    void onCreateLessonPlan(int lessonId, int teamId, String schedule);
    void onDeleteLesson(int lessonId);
    void onEditLesson(Lesson lesson);
}
