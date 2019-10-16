package com.example.swimminggo.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.swimminggo.view.coach.fragment.LessonAvailableFragment;
import com.example.swimminggo.view.coach.fragment.LessonNewFragment;

public class PageAdapter extends FragmentStatePagerAdapter {
    private int numOfTabs;

    public PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new LessonAvailableFragment();
            case 1:
                return new LessonNewFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
