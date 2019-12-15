package com.example.swimminggo.view.coach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.swimminggo.R;
import com.example.swimminggo.adapter.ListAvailableLessonAdapter;
import com.example.swimminggo.models.Lesson;
import com.example.swimminggo.presenter.LessonPresenter;
import com.example.swimminggo.presenter.presenterImpl.LessonPresenterImpl;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class LessonPlan extends AppCompatActivity {

    RecyclerView recyclerViewLessonPlan;
    List<Lesson> lessons;
    LessonPresenter lessonPresenter;
    FloatingActionButton btnCreateLesson;
    public static Activity lessonPlanActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_create_lesson_plan);
        initComponent();
        initDatabase();
        action();
    }

    private void initComponent(){
        lessonPresenter = new LessonPresenterImpl(this);
        recyclerViewLessonPlan = findViewById(R.id.recycler_view_lesson_plan);

        btnCreateLesson = findViewById(R.id.btn_create_lesson);
        lessonPlanActivity = this;
    }

    public void initDatabase(){
        lessonPresenter.onGetListLesson();
    }

    public void setupListLesson(List<Lesson> lessons) {
        this.lessons = lessons;
        List<Boolean> isCheckeds = new ArrayList<>();
        for (Lesson lesson : lessons) {
            isCheckeds.add(false);
        }
        recyclerViewLessonPlan.setAdapter(new ListAvailableLessonAdapter(lessons));
        recyclerViewLessonPlan.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private void action(){
        btnCreateLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LessonPlan.this, CreateLesson.class));
            }
        });
    }

}
