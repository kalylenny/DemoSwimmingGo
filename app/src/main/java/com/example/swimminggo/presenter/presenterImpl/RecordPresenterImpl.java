package com.example.swimminggo.presenter.presenterImpl;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.swimminggo.constant.URLConstant;
import com.example.swimminggo.models.Lesson;
import com.example.swimminggo.models.Record;
import com.example.swimminggo.models.Swimmer;
import com.example.swimminggo.presenter.RecordPresenter;
import com.example.swimminggo.singleton.ListSwimmer;
import com.example.swimminggo.singleton.UserProfile;
import com.example.swimminggo.view.coach.CreateRecord;
import com.example.swimminggo.view.coach.CreateRecordWithExercise;
import com.example.swimminggo.view.coach.CreateRecordWithLesson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecordPresenterImpl implements RecordPresenter {

    private CreateRecord createRecord;
    private CreateRecordWithExercise createRecordWithExercise;
    private CreateRecordWithLesson createRecordWithLesson;

    public RecordPresenterImpl(CreateRecord createRecord) {
        this.createRecord = createRecord;
        AndroidNetworking.initialize(createRecord.getApplicationContext());
    }

    public RecordPresenterImpl(CreateRecordWithExercise createRecordWithExercise){
        this.createRecordWithExercise = createRecordWithExercise;
        AndroidNetworking.initialize(createRecordWithExercise.getApplicationContext());
    }

    public RecordPresenterImpl(CreateRecordWithLesson createRecordWithLesson){
        this.createRecordWithLesson = createRecordWithLesson;
        AndroidNetworking.initialize(createRecordWithLesson.getApplicationContext());
    }

    @Override
    public void onCreateRecord(ArrayList<Record> records) {
        AndroidNetworking.post(URLConstant.getInstance().URL_CREATE_RECORD)
                .addJSONObjectBody(recordToJSONObject(records))
                .addHeaders("Authorization", "Bearer " + UserProfile.getInstance().accessToken)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getBoolean("success")){
                                createRecordWithLesson.doCreateRecord(true);
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

    private JSONObject recordToJSONObject(ArrayList<Record> records){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for(Record record : records){
            jsonArray.put(record.toJSONObject());
        }
        try {
            jsonObject.put("records", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    @Override
    public void onGetLesson(int lessonId) {
        AndroidNetworking.get(URLConstant.getInstance().getUrlGetLessonById(lessonId))
                .addHeaders("Authorization", "Bearer " + UserProfile.getInstance().accessToken)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getBoolean("success")) {
                                createRecord.setupLesson(new Lesson(response.getJSONObject("lesson")));
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
    public void onGetListSwimmerByTeamId(int teamId) {
        AndroidNetworking.get(URLConstant.getInstance().getUrlGetListSwimmer(teamId))
                .addHeaders("Authorization", "Bearer " + UserProfile.getInstance().accessToken)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getBoolean("success")) {
                                List<Swimmer> swimmers = new ArrayList<>();
                                JSONArray swimmerJSONArray = response.getJSONArray("swimmers");
                                for (int i = 0; i < swimmerJSONArray.length(); i++) {
                                    swimmers.add(new Swimmer(swimmerJSONArray.getJSONObject(i)));
                                }
                                createRecordWithExercise.setupRecyclerView(swimmers);
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
