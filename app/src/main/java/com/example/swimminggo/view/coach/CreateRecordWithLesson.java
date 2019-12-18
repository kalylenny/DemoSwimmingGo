package com.example.swimminggo.view.coach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.swimminggo.R;
import com.example.swimminggo.adapter.LessonAdapter;
import com.example.swimminggo.models.Exercise;
import com.example.swimminggo.models.Lesson;
import com.example.swimminggo.models.Record;
import com.example.swimminggo.presenter.LessonPresenter;
import com.example.swimminggo.presenter.RecordPresenter;
import com.example.swimminggo.presenter.presenterImpl.LessonPresenterImpl;
import com.example.swimminggo.presenter.presenterImpl.RecordPresenterImpl;
import com.example.swimminggo.singleton.TotalRecord;

import java.util.ArrayList;
import java.util.List;

public class CreateRecordWithLesson extends AppCompatActivity {

    RecyclerView recyclerView;
    LessonPresenter lessonPresenter;
    static Lesson lesson;
    Button btnFinish;
    static List<List<Record>> lists = new ArrayList<>();
    static int phaseId;
    static int teamId;
    static String date;
    RecordPresenter recordPresenter;
    Activity fa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_record_with_lesson);
        initComponent();
        initDatabase();
        action();
    }

    private void action() {
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phaseId != 3) {
                    finish();
                } else if (isFullList()) {
                    ArrayList<Record> records = new ArrayList<>();
                    for (ArrayList<Record> listRecords : TotalRecord.getInstance().getTotalRecord()) {
                        for (Record record : listRecords) {
                            records.add(record);
                        }
                    }
                    recordPresenter.onCreateRecord(records);
                } else {
                    Toast.makeText(CreateRecordWithLesson.this, "Nhập chưa xong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isFullList() {
        for (ArrayList<Record> records : TotalRecord.getInstance().getTotalRecord()) {
            if (records.size() == 0) {
                return false;
            }
        }
        return true;
    }

    private void initComponent() {
        date = getIntent().getStringExtra("date");
        teamId = getIntent().getIntExtra("team_id", 0);
        lesson = (Lesson) getIntent().getSerializableExtra("lesson");
        phaseId = getIntent().getIntExtra("phase_id", 0);
        lessonPresenter = new LessonPresenterImpl(this);
        recordPresenter = new RecordPresenterImpl(this);
        btnFinish = findViewById(R.id.btn_finish);
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void initDatabase() {
        lessonPresenter.onGetListExerciseByPhaseId(phaseId, lesson.getId());
    }

    public void setUpRecyclerView(List<Exercise> exercises) {
        if (TotalRecord.getInstance().getTotalRecord().size() == 0) {
            for (Exercise exercise : exercises) {
                TotalRecord.getInstance().getTotalRecord().add(new ArrayList<>());
            }
        }
        LessonAdapter lessonAdapter = new LessonAdapter(exercises, teamId, phaseId, date);
        recyclerView.setAdapter(lessonAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(CreateRecordWithLesson.this, LinearLayoutManager.VERTICAL, false));

    }

    public void doCreateRecord(boolean b) {
        if (b) {
            CreateRecord.fa.finish();
            finish();
        }
    }
}
