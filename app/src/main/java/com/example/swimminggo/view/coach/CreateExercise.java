package com.example.swimminggo.view.coach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.swimminggo.R;
import com.example.swimminggo.constant.ExerciseConstant;
import com.example.swimminggo.models.Distance;
import com.example.swimminggo.models.Exercise;
import com.example.swimminggo.models.Phase;
import com.example.swimminggo.models.Style;
import com.example.swimminggo.presenter.ExercisePresenter;
import com.example.swimminggo.presenter.presenterImpl.ExercisePresenterImpl;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.ArrayList;
import java.util.List;

public class CreateExercise extends AppCompatActivity {

    Spinner spnPhase, spnDistance, spnStyle, spnRep;
    ArrayAdapter phaseAdapter, distanceAdapter, styleAdapter, repAdapter;
    Button btnCreate;
    ExercisePresenter exercisePresenter;
    ExerciseConstant exerciseConstant = ExerciseConstant.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exercise);
        initComponent();
        initDatabase();
        action();
    }

    private void initComponent() {
        exercisePresenter = new ExercisePresenterImpl(this);
        spnPhase = findViewById(R.id.spn_phase);
        spnDistance = findViewById(R.id.spn_distance);
        spnStyle = findViewById(R.id.spn_swimstyle);
        spnRep = findViewById(R.id.spn_rep);
        btnCreate = findViewById(R.id.btn_create);
    }

    private void action() {
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exercisePresenter.onCreateExercise(initExercise());
            }
        });
    }


    public void initDatabase() {
        setupDistance();
        setupPhase();
        setupStyle();
        setupRep();
    }

    public void setupPhase() {
        phaseAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, exerciseConstant.getPhases());
        spnPhase.setAdapter(phaseAdapter);
    }

    public void setupDistance() {
        distanceAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, exerciseConstant.getDistances());
        spnDistance.setAdapter(distanceAdapter);
    }

    public void setupStyle() {
        styleAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, exerciseConstant.getStyles());
        spnStyle.setAdapter(styleAdapter);
    }

    public void setupRep() {
        repAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, exerciseConstant.getReps());
        spnRep.setAdapter(repAdapter);
    }

    public void doCreateExercise(boolean result, String message) {
        if (result) {
            finish();
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    public Exercise initExercise() {
        String styleId = Iterables.tryFind(exerciseConstant.getStyles(),
                new Predicate<Style>() {
                    @Override
                    public boolean apply(@NullableDecl Style input) {
                        return input.getValue().equals(spnStyle.getSelectedItem().toString());
                    }
                }).orNull().getId();
        int distance = Integer.parseInt(spnDistance.getSelectedItem().toString());
        int phaseId = Iterables.tryFind(exerciseConstant.getPhases(),
                new Predicate<Phase>() {
                    @Override
                    public boolean apply(@NullableDecl Phase input) {
                        return input.getValue().equals(spnPhase.getSelectedItem().toString());
                    }
                }).orNull().getId();
        int rep = Integer.parseInt(spnRep.getSelectedItem().toString());
        return new Exercise(styleId, distance, rep, phaseId);
    }

}
