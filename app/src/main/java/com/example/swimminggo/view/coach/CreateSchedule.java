package com.example.swimminggo.view.coach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swimminggo.R;
import com.example.swimminggo.adapter.ListAvailableLessonAdapter;
import com.example.swimminggo.models.Lesson;
import com.example.swimminggo.presenter.LessonPresenter;
import com.example.swimminggo.presenter.presenterImpl.LessonPresenterImpl;
import com.example.swimminggo.singleton.ListTeam;
import com.example.swimminggo.singleton.Position;
import com.example.swimminggo.view.coach.fragment.CalendarFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateSchedule extends AppCompatActivity {

    Button btnCalendar, btnAdd;
    Spinner spnTeam;
    RecyclerView recyclerViewLesson;
    LessonPresenter lessonPresenter;
    List<Lesson> lessons = new ArrayList<>();
    String schedule;
    TextView date;
    CalendarFragment calendarFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_schedule);
        initComponent();
        initDatabase();
        action();
        actionCalendar();
    }

    private void initComponent() {
        lessonPresenter = new LessonPresenterImpl(this);
        date = (TextView) findViewById(R.id.date);
        btnCalendar = (Button) findViewById(R.id.btn_calendar);
        btnAdd = findViewById(R.id.btn_add);
        recyclerViewLesson = findViewById(R.id.list_lesson);
        spnTeam = findViewById(R.id.spn_team);
    }

    private void initDatabase() {
        initDate();
        initListTeam();
        lessonPresenter.onGetListLesson();
    }

    private void initListTeam() {
        spnTeam.setAdapter(new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, ListTeam.getInstance().getListTeam()));
    }

    public void setupListLesson(List<Lesson> lessons) {
        this.lessons = lessons;
        List<Boolean> isCheckeds = new ArrayList<>();
        for (Lesson lesson : lessons) {
            isCheckeds.add(false);
        }
        recyclerViewLesson.setAdapter(new ListAvailableLessonAdapter(lessons));
        recyclerViewLesson.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private void initDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        date.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
        schedule = year + "-" + (month + 1) + "-" + dayOfMonth;
    }

    private void action() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Position.getInstance().getLastPosition() != -1) {
                    int teamId = ListTeam.getInstance().getListTeam().get((int) spnTeam.getSelectedItemId()).getTeamID();
                    lessonPresenter.onCreateLessonPlan(lessons.get(Position.getInstance().getLastPosition()).getId(),
                            teamId, schedule);
                } else {
                    Toast.makeText(view.getContext(), "Vui lòng chọn giáo án", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void doCreateLessonPlan(Boolean result) {
        if (result) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            finish();
            this.calendarFragment = (CalendarFragment) CalendarFragment.calendarFragment;
            this.calendarFragment.initDatabase();
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
