package com.example.swimminggo.presenter;

import com.example.swimminggo.models.Record;

import java.util.ArrayList;
import java.util.List;

public interface RecordPresenter {
    void onCreateRecord(ArrayList<Record> records);
    void onGetLesson(int lessonId);
    void onGetListSwimmerByTeamId(int teamId);
}
