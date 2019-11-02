package com.example.swimminggo.view.swimmer.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.swimminggo.R;

import java.util.Calendar;


public class SwimmerWorkoutFragment extends Fragment {

    Button btn_calendar;
    View view;
    TextView date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_swimmer_workout, container, false);
        actionCalendar();
        return  view;
    }

    private void actionCalendar() {
        date = (TextView) view.findViewById(R.id.date);
        btn_calendar = (Button) view.findViewById(R.id.btn_calendar);
        btn_calendar.setOnClickListener(new View.OnClickListener() {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override

                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        date.setText(day + "/" + (month+1) + "/" + year);
                    }
                },year,month,dayOfMonth);
                datePickerDialog.show();
            }
        });
    }


}
