package com.example.swimminggo.view.coach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swimminggo.R;
import com.example.swimminggo.adapter.RecordAdapter;
import com.example.swimminggo.constant.ExerciseConstant;
import com.example.swimminggo.models.Exercise;
import com.example.swimminggo.models.Record;
import com.example.swimminggo.models.Style;
import com.example.swimminggo.models.Swimmer;
import com.example.swimminggo.presenter.RecordPresenter;
import com.example.swimminggo.presenter.presenterImpl.RecordPresenterImpl;
import com.example.swimminggo.singleton.Records;
import com.example.swimminggo.singleton.TotalRecord;
import com.example.swimminggo.singleton.UserProfile;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.List;

public class CreateRecordWithExercise extends AppCompatActivity {

    TextView txtDistance, txtStyle, txtRepetition;
    Button btnCreateRecord;
    RecyclerView recyclerView;
    Exercise exercise;
    RecordPresenter recordPresenter;
    List<Swimmer> swimmers;
    static int position;
    static int teamId;
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_record_with_exercise);
        initComponent();
        initDatabase();
        action();
    }

    private void initComponent(){
        exercise = (Exercise) getIntent().getSerializableExtra("exercise");
        date = getIntent().getStringExtra("date");
        if (position == 0)
            position = getIntent().getIntExtra("position", 0);
        if (teamId == 0)
            teamId = getIntent().getIntExtra("team_id", 0);
        recordPresenter = new RecordPresenterImpl(this);
        txtDistance = findViewById(R.id.txt_distance);
        txtStyle = findViewById(R.id.txt_style);
        txtRepetition = findViewById(R.id.txt_repetition);
        recyclerView = findViewById(R.id.recyclerView);
        btnCreateRecord = findViewById(R.id.btn_create_record);
    }

    private void initDatabase(){
        txtRepetition.setText("Số lần lặp : "+ exercise.getRep());
        txtDistance.setText("Khoảng cách : " + exercise.getDistance()+"");
        String style = Iterables.tryFind(ExerciseConstant.getInstance().getStyles(),
                new Predicate<Style>() {
                    @Override
                    public boolean apply(@NullableDecl Style input) {
                        return input.getId().equals(exercise.getStyleId());
                    }
                }).orNull().getValue();
        txtStyle.setText("Kiểu bơi : " + style);
        Records.newInstance();
        recordPresenter.onGetListSwimmerByTeamId(teamId);
    }

    public void setupRecyclerView(List<Swimmer> swimmers){
        this.swimmers = swimmers;
        for(Swimmer swimmer:swimmers){
            Records.getInstance().getRecords().add(new Record(swimmer.getId(), UserProfile.getInstance().currentUser.getId(), exercise.getId(), date));
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new RecordAdapter(swimmers));
    }

    private void action(){
        btnCreateRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TotalRecord.getInstance().getTotalRecord().set(position, Records.getInstance().getRecords());
                finish();
            }
        });
    }
}
