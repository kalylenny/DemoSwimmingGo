package com.example.swimminggo.adapter;

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
    @NonNull
    @Override
    public ScheduleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, parent, false);
        return new ViewHolder(itemView);
    }

    public ScheduleAdapter(List<Date> dates, CalendarFragment calendarFragment, Date currentDate){
        this.dates = dates;
        this.calendarFragment = calendarFragment;
        this.currentDate = currentDate;
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleAdapter.ViewHolder holder, int position) {
        holder.txtTh.setText(dates.get(position).getFullName());
        holder.txtDay.setText(dates.get(position).getDay()+"");

        if (currentDate.equals(dates.get(position))){
            calendarFragment.onGetListLessonPlanByDate(currentDate);
        }

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarFragment.onGetListLessonPlanByDate(dates.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return dates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtDay, txtTh;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDay = itemView.findViewById(R.id.txt_day);
            txtTh = itemView.findViewById(R.id.txt_th);
        }
    }
}
