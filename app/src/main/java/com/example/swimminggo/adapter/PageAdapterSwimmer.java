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
    private int swimmerId;

    public PageAdapterSwimmer(FragmentManager fm, int numOfTabs, int swimmerId) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.swimmerId = swimmerId;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new SwimmerMonthChartFragment(swimmerId);
            case 1:
                return new SwimmerQuarterChartFragment(swimmerId);
            case 2:
                return new SwimmerYearChartFragment(swimmerId);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
