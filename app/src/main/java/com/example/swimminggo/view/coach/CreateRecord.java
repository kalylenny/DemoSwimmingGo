package com.example.swimminggo.view.coach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.swimminggo.R;
import com.example.swimminggo.models.Exercise;
import com.example.swimminggo.models.Lesson;
import com.example.swimminggo.models.LessonPlan;
import com.example.swimminggo.models.Record;
import com.example.swimminggo.presenter.RecordPresenter;
import com.example.swimminggo.presenter.presenterImpl.RecordPresenterImpl;
import com.example.swimminggo.view.CreateRecordWithLesson;

import java.util.List;

public class CreateRecord extends AppCompatActivity {

    Button btnFinish, btnWarmUp, btnMainStroke, btnFinalSet, btnSwimDown;
    LessonPlan lessonPlan;
    Lesson lesson;
    RecordPresenter recordPresenter;
    List<Record> warmUpRecords, mainStrokeRecords, finalSetRecords, swimDownRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        initComponent();
        action();
    }

    private void initComponent(){
        recordPresenter = new RecordPresenterImpl(this);
        lessonPlan = (LessonPlan) getIntent().getSerializableExtra("lesson_plan");
        btnFinalSet = findViewById(R.id.btn_final_set);
        btnWarmUp = findViewById(R.id.btn_warm_up);
        btnMainStroke = findViewById(R.id.btn_main_stroke);
        btnSwimDown = findViewById(R.id.btn_swim_down);
        btnFinish = findViewById(R.id.btn_finish);

        recordPresenter.onGetLesson(lessonPlan.getLessonId());
    }

    public void setupLesson(Lesson lesson){
        this.lesson = lesson;
    }

    private void moveToActivity(int phaseId){
        Intent intent = new Intent(CreateRecord.this, CreateRecordWithLesson.class);
        intent.putExtra("lesson", lesson);
        intent.putExtra("phase_id", phaseId);
        startActivity(intent);
    }

    private void action(){
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkValidRecord()) {
                    Toast.makeText(CreateRecord.this, "Ghi thành tích thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CreateRecord.this, MainActivity.class));
                }
            }
        });

        btnWarmUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToActivity(1 );
            }
        });
        btnMainStroke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToActivity(2 );
            }
        });
        btnFinalSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToActivity(3 );
            }
        });
        btnSwimDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToActivity(4 );
            }
        });
    }

    private boolean checkValidRecord(){
        if (warmUpRecords == null){
            Toast.makeText(this, "Nhập thành tích cho khởi động", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mainStrokeRecords == null){
            Toast.makeText(this, "Nhập thành tích cho MainStroke", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (finalSetRecords == null){
            Toast.makeText(this, "Nhập thành tích cho finalSetRecord", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (swimDownRecords == null){
            Toast.makeText(this, "Nhập thành tích cho Swimdown", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
