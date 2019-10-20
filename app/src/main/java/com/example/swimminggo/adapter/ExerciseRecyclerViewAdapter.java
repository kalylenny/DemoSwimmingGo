package com.example.swimminggo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swimminggo.R;
import com.example.swimminggo.constant.ExerciseConstant;
import com.example.swimminggo.models.Exercise;
import com.example.swimminggo.models.Style;
import com.example.swimminggo.singleton.ListExerciseByPhase;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.List;

public class ExerciseRecyclerViewAdapter extends RecyclerView.Adapter<ExerciseRecyclerViewAdapter.ViewHolder> {

    View itemView;
    ListExerciseByPhase exerciseByPhase = ListExerciseByPhase.getInstance();
    List<Exercise> exercises;

    @NonNull
    @Override
    public ExerciseRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_exercise, parent, false);

        return new ViewHolder(itemView);
    }

    public ExerciseRecyclerViewAdapter(List<Exercise> exercises){
        this.exercises = exercises;
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseRecyclerViewAdapter.ViewHolder holder, int position) {
        Exercise exercise = exercises.get(position);
        String style = Iterables.tryFind(ExerciseConstant.getInstance().getStyles(),
                new Predicate<Style>() {
                    @Override
                    public boolean apply(@NullableDecl Style input) {
                        return input.getId().equals(exercise.getStyleId());
                    }
                }).orNull().getValue();
        holder.txtStyle.setText(style);
        holder.txtDistance.setText(exercise.getDistance()+"");
        holder.txtRep.setText(exercise.getRep()+"");
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                exerciseByPhase.getIsCheckeds().set(position, isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtStyle, txtDistance, txtRep;
        CheckBox checkBox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtStyle = itemView.findViewById(R.id.txt_style);
            txtDistance = itemView.findViewById(R.id.txt_distance);
            txtRep = itemView.findViewById(R.id.txt_rep);
            checkBox = itemView.findViewById(R.id.check_box);
        }
    }
}
