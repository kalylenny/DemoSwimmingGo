package com.example.swimminggo.view.coach.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swimminggo.R;
import com.example.swimminggo.adapter.ListAvailableLessonAdapter;
import com.example.swimminggo.models.Lesson;
import com.example.swimminggo.singleton.ListTeam;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class LessonAvailableFragment extends Fragment {

    Button btnCalendar, btnAdd;
    Spinner spnTeam;
    RecyclerView recyclerViewLesson;
    View view;
    List<Lesson> lessons = new ArrayList<>();
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
        date = (TextView) view.findViewById(R.id.date);
        btnCalendar = (Button) view.findViewById(R.id.btn_calendar);
        btnAdd = view.findViewById(R.id.btn_add);
        recyclerViewLesson = view.findViewById(R.id.list_lesson);
        spnTeam = view.findViewById(R.id.spn_team);
    }

    private void initDatabase() {
        initDate();
        initListTeam();

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
                    }
                }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });
    }
}
