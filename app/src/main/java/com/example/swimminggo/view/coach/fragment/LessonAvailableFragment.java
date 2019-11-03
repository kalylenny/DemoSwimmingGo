package com.example.swimminggo.view.coach.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swimminggo.R;
import com.example.swimminggo.adapter.ListAvailableLessonAdapter;
import com.example.swimminggo.models.Team;
import com.example.swimminggo.singleton.Position;
import com.example.swimminggo.models.Lesson;
import com.example.swimminggo.presenter.LessonPresenter;
import com.example.swimminggo.presenter.presenterImpl.LessonPresenterImpl;
import com.example.swimminggo.singleton.ListTeam;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class LessonAvailableFragment extends Fragment {

    Button btnCalendar, btnAdd;
    Spinner spnTeam;
    RecyclerView recyclerViewLesson;
    LessonPresenter lessonPresenter;
    View view;
    List<Lesson> lessons = new ArrayList<>();
    String schedule;
    TextView date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lesson_available, container, false);
        initComponent();
        initDatabase();
        action();
        actionCalendar();
        return view;
    }


    private void initComponent() {
        lessonPresenter = new LessonPresenterImpl(this);
        date = (TextView) view.findViewById(R.id.date);
        btnCalendar = (Button) view.findViewById(R.id.btn_calendar);
        btnAdd = view.findViewById(R.id.btn_add);
        recyclerViewLesson = view.findViewById(R.id.list_lesson);
        spnTeam = view.findViewById(R.id.spn_team);
    }

    private void initDatabase() {
        initDate();
        initListTeam();
        lessonPresenter.onGetListLesson();
    }

    private void initListTeam() {
        spnTeam.setAdapter(new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, ListTeam.getInstance().getListTeam()));
    }

    public void setupListLesson(List<Lesson> lessons) {
        this.lessons = lessons;
        List<Boolean> isCheckeds = new ArrayList<>();
        for (Lesson lesson : lessons) {
            isCheckeds.add(false);
        }
        recyclerViewLesson.setAdapter(new ListAvailableLessonAdapter(lessons));
        recyclerViewLesson.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
    }

    private void initDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        date.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

    }

    private void action() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int teamId = ListTeam.getInstance().getListTeam().get((int) spnTeam.getSelectedItemId()).getTeamID();
                lessonPresenter.onCreateLessonPlan(lessons.get(Position.getInstance().getLastPosition()).getId(),
                        teamId,schedule);
            }
        });
    }

    public void doCreateLessonPlan(Boolean result){
        if (result){
            Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
        }
    }

    private void actionCalendar() {
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override

                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        date.setText(day + "/" + (month + 1) + "/" + year);
                        schedule = year + "-" + (month + 1) + "-" + day;
                    }
                }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });
    }
}
