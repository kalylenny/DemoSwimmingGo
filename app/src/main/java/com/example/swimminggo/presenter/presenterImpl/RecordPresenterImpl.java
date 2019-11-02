package com.example.swimminggo.presenter.presenterImpl;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.swimminggo.constant.URLConstant;
import com.example.swimminggo.models.Lesson;
import com.example.swimminggo.models.Record;
import com.example.swimminggo.presenter.RecordPresenter;
import com.example.swimminggo.view.coach.CreateRecord;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class RecordPresenterImpl implements RecordPresenter {

    private CreateRecord createRecord;

    public RecordPresenterImpl(CreateRecord createRecord){
        this.createRecord = createRecord;
        AndroidNetworking.initialize(createRecord.getApplicationContext());
    }
    @Override
    public void onCreateRecord(List<Record> records) {

    }

    @Override
    public void onGetLesson(int lessonId) {
        AndroidNetworking.get(URLConstant.getInstance().getUrlGetLessonById(lessonId))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getBoolean("success")){
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

}
