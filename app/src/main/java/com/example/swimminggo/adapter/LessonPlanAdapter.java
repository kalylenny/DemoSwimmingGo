package com.example.swimminggo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swimminggo.R;
import com.example.swimminggo.models.LessonPlan;
import com.example.swimminggo.models.Team;
import com.example.swimminggo.singleton.ListTeam;
import com.example.swimminggo.view.coach.fragment.CalendarFragment;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.List;

public class LessonPlanAdapter extends RecyclerView.Adapter<LessonPlanAdapter.ViewHolder> {

    List<LessonPlan> lessonPlans;
    CalendarFragment calendarFragment;
    View itemView;

    public LessonPlanAdapter(List<LessonPlan> lessonPlans, CalendarFragment calendarFragment) {
        this.lessonPlans = lessonPlans;
        this.calendarFragment = calendarFragment;
    }

    @NonNull
    @Override
    public LessonPlanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lesson_event, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonPlanAdapter.ViewHolder holder, int position) {
        holder.txtLessonName.setText(lessonPlans.get(position).getLessonName());
        holder.txtTeamName.setText(lessonPlans.get(position).getTeamName());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarFragment.showLessonDialog(lessonPlans.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return lessonPlans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtLessonName, txtTeamName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtLessonName = itemView.findViewById(R.id.txt_lesson_name);
            txtTeamName = itemView.findViewById(R.id.txt_team_name);
        }
    }
}
