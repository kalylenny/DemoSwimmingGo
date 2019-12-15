package com.example.swimminggo.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swimminggo.R;
import com.example.swimminggo.adapter.expandable_menu.ExerciseViewHolder;
import com.example.swimminggo.adapter.expandable_menu.PhaseViewHolder;
import com.example.swimminggo.models.Exercise;
import com.example.swimminggo.view.coach.ExerciseActivity;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class ExerciseAdapter extends ExpandableRecyclerViewAdapter<PhaseViewHolder, ExerciseViewHolder> {

    View exerciseView;
    ExerciseActivity exerciseActivity;

    public ExerciseAdapter(List<? extends ExpandableGroup> groups, ExerciseActivity exerciseActivity) {
        super(groups);
        this.exerciseActivity = exerciseActivity;
    }

    @Override
    public PhaseViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phase, parent, false);
        return new PhaseViewHolder(view);
    }

    @Override
    public ExerciseViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercise_view, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(ExerciseViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        holder.onBind((com.example.swimminggo.models.Exercise) group.getItems().get(childIndex),  exerciseActivity);
    }

    @Override
    public void onBindGroupViewHolder(PhaseViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setPhaseTitle(group, group.getTitle());
    }
}
