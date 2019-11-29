package com.example.swimminggo.view.coach.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swimminggo.R;
import com.example.swimminggo.adapter.LessonPlanAdapter;
import com.example.swimminggo.adapter.ScheduleAdapter;
import com.example.swimminggo.models.Date;
import com.example.swimminggo.models.LessonPlan;
import com.example.swimminggo.models.Team;
import com.example.swimminggo.presenter.SchedulePresenter;
import com.example.swimminggo.presenter.presenterImpl.SchedulePresenterImpl;
import com.example.swimminggo.singleton.ListTeam;
import com.example.swimminggo.singleton.UserProfile;
import com.example.swimminggo.view.Notification;
import com.example.swimminggo.view.coach.CreateRecord;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.whiteelephant.monthpicker.MonthPickerDialog;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CalendarFragment extends Fragment {
    View view;
    TextView txtDate;
    Button btnNextWeek, btnPreviousWeek;
    RecyclerView recyclerViewSchedule, recyclerViewLessonPlan;
    ScheduleAdapter scheduleAdapter;
    LessonPlanAdapter lessonPlanAdapter;
    SchedulePresenter schedulePresenter;
    Calendar calendar;

    public CalendarFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calendar, container, false);
        initComponent();
        initDatabase();
        action();
        return view;
    }

    private void initComponent() {
        txtDate = view.findViewById(R.id.txt_date);
        btnNextWeek = view.findViewById(R.id.btn_next_week);
        btnPreviousWeek = view.findViewById(R.id.btn_previous_week);
        recyclerViewSchedule = view.findViewById(R.id.recycler_view_schedule);
        recyclerViewLessonPlan = view.findViewById(R.id.recycler_view_lesson_plan);
        schedulePresenter = new SchedulePresenterImpl(this);
    }

    public void onGetListLessonPlanByDate(Date date) {
        schedulePresenter.loadListLessonPlanByDate(date);
    }

    private void action() {
        btnNextWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.DATE, 6);
                calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
                Date currentDate = new Date(calendar);
                txtDate.setText(currentDate.getMonth() + "/" + currentDate.getYear());
                List<Date> dates = new ArrayList<>();
                for (int i = 0; i < 7; i++) {
                    dates.add(new Date(calendar));
                    calendar.add(Calendar.DATE, 1);
                }
                setScheduleRecyclerView(dates, currentDate);
            }
        });

        btnPreviousWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.DATE, -14);
                calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
                Date currentDate = new Date(calendar);
                txtDate.setText(currentDate.getMonth() + "/" + currentDate.getYear());
                List<Date> dates = new ArrayList<>();
                for (int i = 0; i < 7; i++) {
                    dates.add(new Date(calendar));
                    calendar.add(Calendar.DATE, 1);
                }
                setScheduleRecyclerView(dates, currentDate);
            }
        });

        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar2 = Calendar.getInstance();
                MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(view.getContext(), new MonthPickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(int selectedMonth, int selectedYear) {
                        calendar.set(Calendar.DAY_OF_MONTH, 1);
                        calendar.set(Calendar.MONTH, selectedMonth);
                        calendar.set(Calendar.YEAR, selectedYear);
                        calendar.set(Calendar.DAY_OF_WEEK, calendar.SUNDAY);
                        Date currentDate = new Date(calendar);
                        txtDate.setText(currentDate.getMonth() + "/" + currentDate.getYear());
                        List<Date> dates = new ArrayList<>();
                        for (int i = 0; i < 7; i++) {
                            dates.add(new Date(calendar));
                            calendar.add(Calendar.DATE, 1);
                        }
                        setScheduleRecyclerView(dates, currentDate);
                    }
                }, calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH));
                builder.setTitle("Select month")
                        .setMonthAndYearRange(Calendar.JANUARY, Calendar.DECEMBER, 1900, 2030)
                        .build().show();
            }
        });
    }

    private void initDatabase() {
        calendar = Calendar.getInstance();
        Date currentDate = new Date(calendar);
        txtDate.setText(currentDate.getMonth() + "/" + currentDate.getYear());
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        List<Date> dates = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            dates.add(new Date(calendar));
            calendar.add(Calendar.DATE, 1);
        }
        setScheduleRecyclerView(dates, currentDate);
    }


    private void setScheduleRecyclerView(List<Date> dates, Date currentDate) {
        scheduleAdapter = new ScheduleAdapter(dates, this, currentDate);
        recyclerViewSchedule.setAdapter(scheduleAdapter);
        recyclerViewSchedule.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    public void setUpLessonPlanRecyclerView(List<LessonPlan> lessonPlans) {
        lessonPlanAdapter = new LessonPlanAdapter(lessonPlans, this);
        recyclerViewLessonPlan.setAdapter(lessonPlanAdapter);
        recyclerViewLessonPlan.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
    }

    public void showLessonDialog(LessonPlan lessonPlan) {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_lesson_plan);
        Button btnCreateRecord = dialog.findViewById(R.id.btn_create_record);
        Button btnCancel = dialog.findViewById(R.id.btn_cancel);

        btnCreateRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CreateRecord.class);
                intent.putExtra("lesson_plan", lessonPlan);
                startActivity(intent);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
