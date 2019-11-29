package com.example.swimminggo.view.coach.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swimminggo.R;
import com.example.swimminggo.adapter.CategoryAdapter;
import com.example.swimminggo.constant.ExerciseConstant;
import com.example.swimminggo.singleton.ListExercise;

public class LibraryCategories extends Fragment {

    View view;
    RecyclerView recyclerViewCategories;
    CategoryAdapter categoryAdapter;

    public LibraryCategories() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_library_categories, container, false);
        initComponent();
        initDatabase();
        return view;
    }

    private void initComponent(){
        recyclerViewCategories = view.findViewById(R.id.recycler_view_categories);
    }

    private void initDatabase(){
        categoryAdapter = new CategoryAdapter(ExerciseConstant.getInstance().getStyles(), this);
        recyclerViewCategories.setAdapter(categoryAdapter);
        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
    }
}
