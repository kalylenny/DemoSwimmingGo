package com.example.swimminggo.presenter.presenterImpl;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.swimminggo.constant.URLConstant;
import com.example.swimminggo.models.Exercise;
import com.example.swimminggo.models.Lesson;
import com.example.swimminggo.presenter.LessonPresenter;
import com.example.swimminggo.singleton.UserProfile;
import com.example.swimminggo.view.coach.CreateLesson;
import com.example.swimminggo.view.coach.CreateRecordWithLesson;
import com.example.swimminggo.view.coach.CreateSchedule;
import com.example.swimminggo.view.coach.LessonPlan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LessonPresenterImpl implements LessonPresenter {

    private CreateLesson createLesson;
    private CreateSchedule createSchedule;
    private CreateRecordWithLesson createRecordWithLesson;
    private LessonPlan lessonPlan;

    public LessonPresenterImpl(CreateLesson createLesson) {
        this.createLesson = createLesson;
        AndroidNetworking.initialize(createLesson.getApplicationContext());
    }

    public LessonPresenterImpl(LessonPlan lessonPlan){
        this.lessonPlan = lessonPlan;
        AndroidNetworking.initialize(lessonPlan.getApplicationContext());
    }

    public LessonPresenterImpl(CreateRecordWithLesson createRecordWithLesson){
        this.createRecordWithLesson = createRecordWithLesson;
        AndroidNetworking.initialize(createRecordWithLesson.getApplicationContext());
    }

    public LessonPresenterImpl(CreateSchedule createSchedule){
        this.createSchedule = createSchedule;
        AndroidNetworking.initialize(createSchedule.getApplicationContext());
    }

    @Override
    public void onGetListExerciseByPhaseId(int phaseId, List<Exercise> exerciseList) {
        List<Exercise> exercises = new ArrayList<>();
        for (Exercise exercise : exerciseList)
            if (exercise.getPhaseId() == phaseId) {
                exercises.add(exercise);
            }
        createLesson.setupDialog(phaseId, exercises);
    }

    @Override
    public void onGetListExerciseByPhaseId(int phaseId, int lessonId) {
        AndroidNetworking.get(URLConstant.getInstance().getUrlGetLessonByPhaseId(phaseId, lessonId))
                .addHeaders("Authorization", "Bearer " + UserProfile.getInstance().accessToken)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<Exercise> exerciseList = new ArrayList<>();
                        try {
                            if (response.getBoolean("success")){
                                JSONArray exerciseJSONs = response.getJSONArray("exercise");
                                for (int i = 0; i < exerciseJSONs.length(); i++){
                                    exerciseList.add(new Exercise(exerciseJSONs.getJSONObject(i)));
                                }
                                createRecordWithLesson.setUpRecyclerView(exerciseList);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    @Override
    public void onCreateLesson(List<Exercise> exercises, String name) {
        JSONObject exerciseJSON = toJSONObject(exercises, name);
        AndroidNetworking.post(URLConstant.getInstance().URL_CREATE_LESSON)
                .addHeaders("Authorization", "Bearer " + UserProfile.getInstance().accessToken)
                .addJSONObjectBody(exerciseJSON)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getBoolean("success")) {
                                createLesson.doCreateLesson(true, response.getString("message"));
                            } else {
                                createLesson.doCreateLesson(false, response.getString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    @Override
    public void onGetListLesson() {
        List<Lesson> lessons = new ArrayList<>();
        AndroidNetworking.get(URLConstant.getInstance().URL_GET_LESSON)
                .addHeaders("Authorization", "Bearer " + UserProfile.getInstance().accessToken)
                .build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getBoolean("success")){
                        JSONArray lessonJSONs = response.getJSONArray("lesson");
                        for(int i = 0; i < lessonJSONs.length(); i++){
                            lessons.add(new Lesson(lessonJSONs.getJSONObject(i)));
                        }
                        if (createSchedule != null)
                            createSchedule.setupListLesson(lessons);
                        if (lessonPlan != null){
                            lessonPlan.setupListLesson(lessons);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ANError anError) {

            }
        });

    }

    @Override
    public void onCreateLessonPlan(int lessonId, int teamId, String schedule) {
        AndroidNetworking.post(URLConstant.getInstance().URL_CREATE_LESSON_PLAN)
                .addJSONObjectBody(toLessonPlanObject(lessonId, teamId, schedule))
                .addHeaders("Authorization", "Bearer " + UserProfile.getInstance().accessToken)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getBoolean("success")){
                                createSchedule.doCreateLessonPlan(true);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    @Override
    public void onDeleteLesson(int lessonId) {
        AndroidNetworking.delete(URLConstant.getInstance().getUrlDeleteLesson(lessonId))
                .addHeaders("Authorization", "Bearer " + UserProfile.getInstance().accessToken)
                .build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    if (response.getBoolean("success")){
                        lessonPlan.doDeleteLesson(true, "Tạo thành công");
                    } else {
                        lessonPlan.doDeleteLesson(false, response.getString("message"));
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ANError anError) {

            }
        });
    }

    @Override
    public void onEditLesson(Lesson lesson) {

    }

    private JSONObject toLessonPlanObject(int lessonId, int teamId, String schedule){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("lesson_id", lessonId);
            jsonObject.put("team_id", teamId);
            jsonObject.put("schedule", schedule);
        }catch(Exception e){
            e.printStackTrace();
        }
        return jsonObject;
    }

    private JSONObject toJSONObject(List<Exercise> exercises, String name) {
        JSONObject result = new JSONObject();
        JSONArray exerciseArr = new JSONArray();
        try {
            for (Exercise exercise : exercises) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("exercise_id", exercise.getId());
                exerciseArr.put(jsonObject);
            }
            result.put("exercises", exerciseArr);
            result.put("name", name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
