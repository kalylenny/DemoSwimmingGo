package com.example.swimminggo.presenter;

import com.example.swimminggo.models.Record;

import java.util.List;

public interface RecordPresenter {
    void onCreateRecord(List<Record> records);
    void onGetLesson(int lessonId);

}
