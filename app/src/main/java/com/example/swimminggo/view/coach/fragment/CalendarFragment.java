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

import com.example.swimminggo.R;
import com.example.swimminggo.models.LessonPlan;
import com.example.swimminggo.models.Team;
import com.example.swimminggo.presenter.SchedulePresenter;
import com.example.swimminggo.presenter.presenterImpl.SchedulePresenterImpl;
import com.example.swimminggo.singleton.ListTeam;
import com.example.swimminggo.singleton.UserProfile;
import com.example.swimminggo.view.coach.CreateRecord;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CalendarFragment extends Fragment {
    View view;
    LinearLayout weekLayout;
    Calendar calendar;
    SchedulePresenter schedulePresenter;
    List<LinearLayout> layouts;
    int count = 0;

    public CalendarFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calendar, container, false);
        initComponent();
        initDatabase();
        return view;
    }

    private void initComponent() {
        weekLayout = view.findViewById(R.id.week_layout);
        calendar = Calendar.getInstance();
        schedulePresenter = new SchedulePresenterImpl(this);
        layouts = new ArrayList<>(Arrays.asList(null, null, null, null, null, null, null, null));
    }

    private void action() {

    }

    private void initDatabase() {
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        for (int i = 0; i < 7; i++) {
            schedulePresenter.loadListLessonPlanByDate(calendar.get(Calendar.DATE),
                    calendar.get(Calendar.MONTH) + 1,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.DAY_OF_WEEK));
            calendar.add(Calendar.DATE, 1);
        }
    }

    public void setDayEventInSchedule(String date, int index, List<LessonPlan> lessonPlans) {
        LayoutInflater inflater = LayoutInflater.from(this.getContext());
        LinearLayout dayEventLayout = (LinearLayout) inflater.inflate(R.layout.item_date_event, null);
        TextView txtDate = dayEventLayout.findViewById(R.id.txt_date);
        txtDate.setText(date);
        for (LessonPlan lessonPlan : lessonPlans) {
            LinearLayout lessonEventLayout = (LinearLayout) inflater.inflate(R.layout.item_lesson_event, null);
            TextView txtLessonName = lessonEventLayout.findViewById(R.id.txt_lesson_name);
            TextView txtTeamName;
            if (UserProfile.getInstance().currentUser.getRoleName().equals("coach")) {
                txtTeamName = lessonEventLayout.findViewById(R.id.txt_team_name);
                String teamName = Iterables.tryFind(ListTeam.getInstance().getListTeam(), new Predicate<Team>() {
                    @Override
                    public boolean apply(@NullableDecl Team input) {
                        return input.getTeamID() == lessonPlan.getTeamId();
                    }
                }).orNull().getTeamName();
                txtTeamName.setText(teamName);
            }

            txtLessonName.setText(lessonPlan.getLessonName());

            dayEventLayout.addView(lessonEventLayout);

            lessonEventLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (UserProfile.getInstance().currentUser.getRoleName().equals("coach"))
                        showLessonDialog(lessonPlan);
                }
            });
        }
        weekLayout.addView(dayEventLayout);
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
