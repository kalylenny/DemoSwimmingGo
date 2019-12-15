package com.example.swimminggo.presenter.presenterImpl;

import android.util.Log;

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
import com.example.swimminggo.singleton.ListExercise;
import com.example.swimminggo.singleton.UserProfile;
import com.example.swimminggo.view.Login;
import com.example.swimminggo.view.coach.CreateExercise;
import com.example.swimminggo.view.coach.CreateLesson;
import com.example.swimminggo.view.coach.MainActivity;
import com.example.swimminggo.view.coach.fragment.WorkoutFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ExercisePresenterImpl implements ExercisePresenter {

    CreateExercise createExercise;
    CreateLesson createLesson;
    WorkoutFragment workoutFragment;
    MainActivity mainActivity;
    Login login;

    public ExercisePresenterImpl(Login login){
        this.login = login;
        AndroidNetworking.initialize(login.getApplicationContext());
    }

    public ExercisePresenterImpl(CreateExercise createExercise){
        this.createExercise = createExercise;
        AndroidNetworking.initialize(createExercise.getApplicationContext());
    }

    public ExercisePresenterImpl(CreateLesson createLesson){
        this.createLesson = createLesson;
        AndroidNetworking.initialize(createLesson.getApplicationContext());
    }

    public ExercisePresenterImpl(WorkoutFragment workoutFragment){
        this.workoutFragment = workoutFragment;
        AndroidNetworking.initialize(workoutFragment.getContext());
    }

    public ExercisePresenterImpl(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        AndroidNetworking.initialize(mainActivity.getApplicationContext());
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

    @Override
    public void onGetListExercise() {
        AndroidNetworking.get(URLConstant.getInstance().URL_GET_EXERCISE)
                .addHeaders("Authorization", "Bearer " + UserProfile.getInstance().accessToken)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray exeJSONArray = response.getJSONArray("exercise");
                            for(int i = 0; i < exeJSONArray.length(); i++){
                                ListExercise.getInstance().getExercises().add(new Exercise(exeJSONArray.getJSONObject(i)));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("error", "onError: " + anError);
                    }
                });
    }
}
