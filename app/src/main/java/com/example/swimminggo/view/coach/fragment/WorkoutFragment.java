package com.example.swimminggo.view.coach.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.swimminggo.R;
import com.example.swimminggo.constant.ExerciseConstant;
import com.example.swimminggo.presenter.ExercisePresenter;
import com.example.swimminggo.presenter.presenterImpl.ExercisePresenterImpl;
import com.example.swimminggo.view.coach.ExerciseActivity;
import com.example.swimminggo.view.coach.LessonPlan;

public class WorkoutFragment extends Fragment {
    View view;
    Button btn_excercise, btn_lesson_plan;

    public WorkoutFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_workout, container, false);
        button();
        initDatabase();
        return view;
    }

    public void button() {
        btn_excercise = (Button) view.findViewById(R.id.create_exercise);
        btn_lesson_plan = (Button) view.findViewById(R.id.lesson_plan);
        btn_excercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ExerciseActivity.class);
                startActivity(intent);
            }
        });
        btn_lesson_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LessonPlan.class);
                startActivity(intent);
            }
        });
    }

    private void initDatabase() {
        ExercisePresenter exercisePresenter = new ExercisePresenterImpl(this);
        if (ExerciseConstant.getInstance() == null) {
            ExerciseConstant.newInstance();
            exercisePresenter.onGetStyles();
            exercisePresenter.onGetDistances();
            exercisePresenter.onGetPhases();
        }
    }
}
