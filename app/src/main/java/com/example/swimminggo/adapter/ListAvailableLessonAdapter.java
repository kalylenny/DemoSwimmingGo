package com.example.swimminggo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swimminggo.R;
import com.example.swimminggo.models.Lesson;

import java.util.List;

public class ListAvailableLessonAdapter extends RecyclerView.Adapter<ListAvailableLessonAdapter.ViewHolder> {

    List<Lesson> lessons;
    private int lastCheckedPosition = -1;

    @NonNull
    @Override
    public ListAvailableLessonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simple_lesson_row, parent, false);
        return new ListAvailableLessonAdapter.ViewHolder(itemView);
    }

    public ListAvailableLessonAdapter(List<Lesson> lessons){
        this.lessons = lessons;
    }

    @Override
    public void onBindViewHolder(@NonNull ListAvailableLessonAdapter.ViewHolder holder, int position) {
        holder.lessonName.setText(lessons.get(position).getName());
        holder.radioButton.setChecked(position == lastCheckedPosition);
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView lessonName;
        private RadioButton radioButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lessonName = itemView.findViewById(R.id.lesson_name);
            radioButton = itemView.findViewById(R.id.radio_button);
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    lastCheckedPosition = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
        }
    }
}
