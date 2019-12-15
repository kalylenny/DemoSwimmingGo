package com.example.swimminggo.view.coach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.swimminggo.R;
import com.example.swimminggo.adapter.ExerciseAdapter;
import com.example.swimminggo.adapter.expandable_menu.Phase;
import com.example.swimminggo.constant.ExerciseConstant;
import com.example.swimminggo.models.Distance;
import com.example.swimminggo.models.Exercise;
import com.example.swimminggo.models.Style;
import com.example.swimminggo.presenter.ExercisePresenter;
import com.example.swimminggo.presenter.presenterImpl.ExercisePresenterImpl;
import com.example.swimminggo.singleton.ListExercise;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ExerciseActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ExercisePresenter exercisePresenter;
    FloatingActionButton btnCreateExercise;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        initComponent();
        initDatabase();
        action();
    }

    private void initComponent(){
        exercisePresenter = new ExercisePresenterImpl(this);
        recyclerView = findViewById(R.id.recyclerView);
        btnCreateExercise = findViewById(R.id.btn_create_exercise);

        dialog = new Dialog(this);
    }

    public void setupRecyclerView(){
        List<Phase> phases = getPhases();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        ExerciseAdapter exerciseAdapter = new ExerciseAdapter(phases, this);
        recyclerView.setAdapter(exerciseAdapter);
        recyclerView.setLayoutManager(layoutManager);

    }

    public void initDatabase(){
        exercisePresenter.onGetListExercise();
    }

    private List<Phase> getPhases(){
        List<Phase> phases = new ArrayList<>();
        Phase warmUpPhase = new Phase("Warm Up", getExercisesByPhasesId(1));
        Phase mainStrokePhase = new Phase("Main Stroke", getExercisesByPhasesId(2));
        Phase finalSetPhase = new Phase("Final Set", getExercisesByPhasesId(3));
        Phase swimDownPhase = new Phase("Swim Down", getExercisesByPhasesId(4));
        phases.add(warmUpPhase);
        phases.add(mainStrokePhase);
        phases.add(finalSetPhase);
        phases.add(swimDownPhase);
        return phases;
    }

    private List<Exercise> getExercisesByPhasesId(int phaseId){
        List<Exercise> exercises = new ArrayList<>();
        for (Exercise exercise : ListExercise.getInstance().getExercises())
            if (exercise.getPhaseId() == phaseId)
                exercises.add(exercise);
        return exercises;
    }

    public void onDeleteExercise(int exerciseId){
        exercisePresenter.onDeleteExercise(exerciseId);
    }

    public void doDeleteExercise(boolean result, String message) {
        if (result){
            initDatabase();
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }

    }

    public void doEditExercise(boolean b, String success) {
        if (b){
            initDatabase();
        } else {
            Toast.makeText(this, success, Toast.LENGTH_SHORT).show();
        }
    }

    public void onEditExercise(Exercise exercise) {
        exercisePresenter.onEditExercise(exercise);
    }

    private void action(){
        btnCreateExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.dialog_add_exercise);
                Spinner spnPhase, spnDistance, spnRep, spnStyle;
                Button btnAdd, btnCancel;

                btnAdd = dialog.findViewById(R.id.btn_add);
                btnCancel = dialog.findViewById(R.id.btn_cancel);

                spnPhase = dialog.findViewById(R.id.spn_phase);
                spnDistance = dialog.findViewById(R.id.spn_distance);
                spnRep = dialog.findViewById(R.id.spn_rep);
                spnStyle = dialog.findViewById(R.id.spn_style);

                spnPhase.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, ExerciseConstant.getInstance().getPhases()));
                spnRep.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, ExerciseConstant.getInstance().getReps()));
                spnDistance.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, ExerciseConstant.getInstance().getDistances()));
                spnStyle.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, ExerciseConstant.getInstance().getStyles()));

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Style style = (Style) spnStyle.getSelectedItem();
                        Distance distance = (Distance) spnDistance.getSelectedItem();
                        com.example.swimminggo.models.Phase phase = (com.example.swimminggo.models.Phase) spnPhase.getSelectedItem();
                        exercisePresenter.onCreateExercise(new Exercise(style.getId(), distance.getValue(),(int) spnRep.getSelectedItem(), phase.getId()));
                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

    public void doCreateExercise(boolean result, String message) {
        if (result) {
            dialog.dismiss();
            initDatabase();
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
}
