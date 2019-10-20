package com.example.swimminggo.presenter.presenterImpl;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.swimminggo.constant.URLConstant;
import com.example.swimminggo.models.Exercise;
import com.example.swimminggo.presenter.LessonPresenter;
import com.example.swimminggo.singleton.UserProfile;
import com.example.swimminggo.view.coach.fragment.LessonNewFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LessonPresenterImpl implements LessonPresenter {

    private LessonNewFragment lessonNewFragment;

    public LessonPresenterImpl(LessonNewFragment lessonNewFragment) {
        this.lessonNewFragment = lessonNewFragment;
        AndroidNetworking.initialize(lessonNewFragment.getContext());
    }

    @Override
    public void onGetListExerciseByPhaseId(int phaseId, List<Exercise> exerciseList) {
        List<Exercise> exercises = new ArrayList<>();
        for (Exercise exercise : exerciseList)
            if (exercise.getPhaseId() == phaseId) {
                exercises.add(exercise);
            }
        lessonNewFragment.setupDialog(phaseId, exercises);
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
                            if (response.getBoolean("success")){
                                lessonNewFragment.doCreateLesson(true, "Success");
                            } else {
                                lessonNewFragment.doCreateLesson(false, "False");
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

    private JSONObject toJSONObject(List<Exercise> exercises, String name) {
        JSONObject result = new JSONObject();
        JSONArray exerciseArr = new JSONArray();
        try {
            for(Exercise exercise : exercises){
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
