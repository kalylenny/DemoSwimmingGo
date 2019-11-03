package com.example.swimminggo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swimminggo.R;
import com.example.swimminggo.singleton.Position;
import com.example.swimminggo.models.Lesson;

import java.util.List;

public class ListAvailableLessonAdapter extends RecyclerView.Adapter<ListAvailableLessonAdapter.ViewHolder> {

    List<Lesson> lessons;

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
        holder.checkBox.setChecked(position == Position.getInstance().getLastPosition());
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView lessonName;
        private CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lessonName = itemView.findViewById(R.id.lesson_name);
            checkBox = itemView.findViewById(R.id.check_box);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Position.getInstance().setLastPosition(getAdapterPosition());
                    notifyDataSetChanged();
                }
            });
        }
    }
}
