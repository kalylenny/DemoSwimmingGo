package com.example.swimminggo.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.swimminggo.view.swimmer.fragment.SwimmerMonthChartFragment;
import com.example.swimminggo.view.swimmer.fragment.SwimmerQuarterChartFragment;
import com.example.swimminggo.view.swimmer.fragment.SwimmerYearChartFragment;

public class PageAdapterSwimmer extends FragmentStatePagerAdapter {

    private  int numOfTabs;

    public PageAdapterSwimmer(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new SwimmerMonthChartFragment();
            case 1:
                return new SwimmerQuarterChartFragment();
            case 2:
                return new SwimmerYearChartFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
