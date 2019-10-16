package com.example.swimminggo.presenter.presenterImpl;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.swimminggo.constant.ExerciseConstant;
import com.example.swimminggo.constant.URLConstant;
import com.example.swimminggo.models.Distance;
import com.example.swimminggo.models.Exercise;
import com.example.swimminggo.models.Phase;
import com.example.swimminggo.models.Style;
import com.example.swimminggo.presenter.ExercisePresenter;
import com.example.swimminggo.singleton.UserProfile;
import com.example.swimminggo.view.coach.CreateExercise;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ExercisePresenterImpl implements ExercisePresenter {

    CreateExercise createExercise;

    public ExercisePresenterImpl(CreateExercise createExercise){
        this.createExercise = createExercise;
        AndroidNetworking.initialize(createExercise.getApplicationContext());
    }

    @Override
    public void onGetPhases() {
        AndroidNetworking.get(URLConstant.getInstance().URL_GET_PHASES)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getBoolean("success")){
                                JSONArray phaseJSONs = response.getJSONArray("values");
                                for(int i = 0; i < phaseJSONs.length(); i++){
                                    ExerciseConstant.getInstance().getPhases().add(new Phase(phaseJSONs.getJSONObject(i)));
                                    createExercise.setupPhase();
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
    public void onGetDistances() {
        AndroidNetworking.get(URLConstant.getInstance().URL_GET_DISNTACES)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getBoolean("success")){
                                JSONArray phaseJSONs = response.getJSONArray("values");
                                for(int i = 0; i < phaseJSONs.length(); i++){
                                    ExerciseConstant.getInstance().getDistances().add(new Distance(phaseJSONs.getJSONObject(i)));
                                    createExercise.setupDistance();
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
    public void onGetStyles() {
        AndroidNetworking.get(URLConstant.getInstance().URL_GET_STYLES)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getBoolean("success")){
                                JSONArray phaseJSONs = response.getJSONArray("values");
                                for(int i = 0; i < phaseJSONs.length(); i++){
                                    ExerciseConstant.getInstance().getStyles().add(new Style(phaseJSONs.getJSONObject(i)));
                                    createExercise.setupStyle();
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
    public void onCreateExercise(Exercise exercise) {
        AndroidNetworking.post(URLConstant.getInstance().URL_CREATE_EXERCISE)
                .addHeaders("Authorization", "Bearer " + UserProfile.getInstance().accessToken)
                .addJSONObjectBody(exercise.toJSONObject())
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getBoolean("success")){
                                createExercise.doCreateExercise(true, "Success");
                            } else {
                                createExercise.doCreateExercise(false, "False");
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
}
