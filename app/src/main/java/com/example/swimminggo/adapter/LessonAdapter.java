package com.example.swimminggo.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swimminggo.R;
import com.example.swimminggo.constant.ExerciseConstant;
import com.example.swimminggo.models.Exercise;
import com.example.swimminggo.models.Style;
import com.example.swimminggo.view.coach.CreateRecordWithExercise;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.io.Serializable;
import java.util.List;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.ViewHolder> {
    List<Exercise> exercises;
    int teamId;
    int phaseId;
    String date;

    public LessonAdapter(List<Exercise> exercises, int teamId, int phaseId, String date) {
        this.exercises = exercises;
        this.teamId = teamId;
        this.phaseId = phaseId;
        this.date = date;
    }

    @NonNull
    @Override
    public LessonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lesson, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonAdapter.ViewHolder holder, int position) {
        Exercise exercise = exercises.get(position);
        String style = Iterables.tryFind(ExerciseConstant.getInstance().getStyles(),
                new Predicate<Style>() {
                    @Override
                    public boolean apply(@NullableDecl Style input) {
                        return input.getId().equals(exercise.getStyleId());
                    }
                }).orNull().getValue();
        holder.txtStyle.setText(style);
        holder.txtDistance.setText(exercise.getDistance() + "");
        holder.txtRepetition.setText(exercise.getRep() + "");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phaseId == 3) {
                    Intent intent = new Intent(holder.itemView.getContext(), CreateRecordWithExercise.class);
                    intent.putExtra("exercise", (Serializable) exercise);
                    intent.putExtra("team_id", teamId);
                    intent.putExtra("position", position);
                    intent.putExtra("date",date);
                    holder.itemView.getContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtStyle, txtDistance, txtRepetition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtStyle = itemView.findViewById(R.id.txt_style);
            txtDistance = itemView.findViewById(R.id.txt_distance);
            txtRepetition = itemView.findViewById(R.id.txt_repetition);
        }
    }
}
