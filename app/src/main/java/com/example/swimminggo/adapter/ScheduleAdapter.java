package com.example.swimminggo.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swimminggo.R;
import com.example.swimminggo.models.Date;
import com.example.swimminggo.view.coach.fragment.CalendarFragment;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
    List<Date> dates;
    CalendarFragment calendarFragment;
    Date currentDate;
    View itemView;
    Integer currentPosition;
    Integer lastPosition;

    @NonNull
    @Override
    public ScheduleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, parent, false);
        return new ViewHolder(itemView);
    }

    public ScheduleAdapter(List<Date> dates, CalendarFragment calendarFragment, Date currentDate) {
        this.dates = dates;
        this.calendarFragment = calendarFragment;
        this.currentDate = currentDate;
        this.currentPosition = dates.indexOf(currentDate);
        this.lastPosition = currentPosition;
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleAdapter.ViewHolder holder, int position) {
        holder.rbDate.setText(dates.get(position).getFullName() + "\n" +dates.get(position).getDay());
        holder.rbDate.setChecked(position == lastPosition);
        if (position == lastPosition){
            calendarFragment.onGetListLessonPlanByDate(dates.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RadioButton rbDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rbDate = itemView.findViewById(R.id.rb_date);

            rbDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastPosition = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });

        }
    }
}
