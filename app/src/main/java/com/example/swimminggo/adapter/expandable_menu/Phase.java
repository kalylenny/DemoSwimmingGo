package com.example.swimminggo.adapter.expandable_menu;

import android.os.Parcel;

import com.example.swimminggo.models.Exercise;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class Phase extends ExpandableGroup<Exercise> {
    public Phase(String title, List<Exercise> items) {
        super(title, items);
    }

    protected Phase(Parcel in) {
        super(in);
    }
}
