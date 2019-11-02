package com.example.swimminggo.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.swimminggo.R;
import com.example.swimminggo.adapter.LessonAdapter;
import com.example.swimminggo.models.Exercise;
import com.example.swimminggo.models.Lesson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CreateRecordWithLesson extends AppCompatActivity {

    RecyclerView recyclerView;
    Lesson lesson;
    int phaseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_record_with_lesson);
        initComponent();
    }

    private void initComponent(){
        lesson = (Lesson) getIntent().getSerializableExtra("lesson");
        phaseId = getIntent().getIntExtra("phase_id", 0);
        recyclerView = findViewById(R.id.recyclerView);
        LessonAdapter lessonAdapter = new LessonAdapter(getExercisesByPhaseId(phaseId));
        recyclerView.setAdapter(lessonAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(CreateRecordWithLesson.this, LinearLayout.VERTICAL, false));
    }

    private List<Exercise> getExercisesByPhaseId(int phaseId){
        List<Exercise> exerciseList = new ArrayList<>();
        for(int i = 0; i < lesson.getExercises().size(); i++){
            exerciseList.add(lesson.getExercises().get(i));
        }
        return exerciseList;
    }
}
