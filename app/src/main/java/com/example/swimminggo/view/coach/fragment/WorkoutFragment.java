package com.example.swimminggo.view.coach.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.swimminggo.R;

public class WorkoutFragment extends Fragment {
    View view;
    public WorkoutFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_workout, container, false);
        return view;
    }
}
