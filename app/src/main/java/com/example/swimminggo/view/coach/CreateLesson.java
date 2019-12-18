package com.example.swimminggo.view.coach;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swimminggo.R;
import com.example.swimminggo.adapter.ExerciseRecyclerViewAdapter;
import com.example.swimminggo.constant.ExerciseConstant;
import com.example.swimminggo.models.Exercise;
import com.example.swimminggo.models.Phase;
import com.example.swimminggo.presenter.ExercisePresenter;
import com.example.swimminggo.presenter.LessonPresenter;
import com.example.swimminggo.presenter.presenterImpl.ExercisePresenterImpl;
import com.example.swimminggo.presenter.presenterImpl.LessonPresenterImpl;
import com.example.swimminggo.singleton.ListExercise;
import com.example.swimminggo.singleton.ListExerciseByPhase;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.ArrayList;
import java.util.List;

public class CreateLesson extends AppCompatActivity {

    EditText edtLessonName;
    Button btnWarmUp, btnMainStroke, btnFinalSet, btnSwimDown, btnCreateLesson;

    List<Exercise> exercises;
    List<Exercise> warmUpExercises, mainStrokeExercises, finalSetExercises, swimDownExercises = new ArrayList<>();

    ExercisePresenter exercisePresenter;
    LessonPresenter lessonPresenter;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_lesson);
        initComponent();
        action();
    }

    private void initComponent() {
        exercisePresenter = new ExercisePresenterImpl(this);

        lessonPresenter = new LessonPresenterImpl(this);
        if (com.example.swimminggo.singleton.ListExercise.getInstance() == null) {
            com.example.swimminggo.singleton.ListExercise.newInstance();
            exercisePresenter.onGetListExercise();
        }
        exercises = ListExercise.getInstance().getExercises();
        btnWarmUp = findViewById(R.id.btn_warm_up);
        btnMainStroke = findViewById(R.id.btn_main_stroke);
        btnFinalSet = findViewById(R.id.btn_final_set);
        btnSwimDown = findViewById(R.id.btn_swim_down);
        btnCreateLesson = findViewById(R.id.btn_create_lesson);

        edtLessonName = findViewById(R.id.edt_lesson_name);
    }

    private void action() {
        btnWarmUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lessonPresenter.onGetListExerciseByPhaseId(1, exercises);
            }
        });
        btnMainStroke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lessonPresenter.onGetListExerciseByPhaseId(2, exercises);
            }
        });
        btnFinalSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lessonPresenter.onGetListExerciseByPhaseId(3, exercises);
            }
        });
        btnSwimDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lessonPresenter.onGetListExerciseByPhaseId(4, exercises);
            }
        });


        btnCreateLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFullExercises()) {
                    List<Exercise> exercises = new ArrayList<>();
                    for (Exercise exercise : warmUpExercises)
                        exercises.add(exercise);
                    for (Exercise exercise : mainStrokeExercises)
                        exercises.add(exercise);
                    for (Exercise exercise : finalSetExercises)
                        exercises.add(exercise);
                    for (Exercise exercise : swimDownExercises)
                        exercises.add(exercise);

                    lessonPresenter.onCreateLesson(exercises, edtLessonName.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isFullExercises() {
        return (warmUpExercises == null || mainStrokeExercises == null || finalSetExercises == null || swimDownExercises == null);
    }

    public void doCreateLesson(boolean result, String message) {
        if (result) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            finish();
            LessonPlan lessonPlan = (LessonPlan) LessonPlan.lessonPlanActivity;
            lessonPlan.initDatabase();
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    public void setupDialog(int phaseId, List<Exercise> exercises) {
        for (Exercise exercise : exercises) {
            ListExerciseByPhase.getInstance().getIsCheckeds().add(false);
        }
        String phaseName = Iterables.tryFind(ExerciseConstant.getInstance().getPhases(),
                new Predicate<Phase>() {
                    @Override
                    public boolean apply(@NullableDecl Phase input) {
                        return input.getId() == phaseId;
                    }
                }).orNull().getValue();

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_exercises);
        Button btnCreate, btnCancel;
        RecyclerView recyclerView = dialog.findViewById(R.id.recyclerView_exercises);
        TextView txtPhaseName = dialog.findViewById(R.id.txt_phase_name);

        btnCreate = dialog.findViewById(R.id.btn_create);
        btnCancel = dialog.findViewById(R.id.btn_cancel);

        txtPhaseName.setText(phaseName);

        ExerciseRecyclerViewAdapter exerciseAdapter = new ExerciseRecyclerViewAdapter(exercises);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(exerciseAdapter);
        dialog.show();

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phaseId == 1) {
                    warmUpExercises = new ArrayList<>();
                    for (int i = 0; i < exercises.size(); i++) {
                        if (ListExerciseByPhase.getInstance().getIsCheckeds().get(i)) {
                            warmUpExercises.add(exercises.get(i));
                        }
                    }
                } else if (phaseId == 2) {
                    mainStrokeExercises = new ArrayList<>();
                    for (int i = 0; i < exercises.size(); i++) {
                        if (ListExerciseByPhase.getInstance().getIsCheckeds().get(i)) {
                            mainStrokeExercises.add(exercises.get(i));
                        }
                    }
                } else if (phaseId == 3) {
                    finalSetExercises = new ArrayList<>();
                    for (int i = 0; i < exercises.size(); i++) {
                        if (ListExerciseByPhase.getInstance().getIsCheckeds().get(i)) {
                            finalSetExercises.add(exercises.get(i));
                        }
                    }
                } else {
                    swimDownExercises = new ArrayList<>();
                    for (int i = 0; i < exercises.size(); i++) {
                        if (ListExerciseByPhase.getInstance().getIsCheckeds().get(i)) {
                            swimDownExercises.add(exercises.get(i));
                        }
                    }
                }
                dialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

}
