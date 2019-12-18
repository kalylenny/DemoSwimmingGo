package com.example.swimminggo.view.swimmer.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.swimminggo.R;
import com.example.swimminggo.adapter.PageAdapterSwimmer;
import com.example.swimminggo.constant.ExerciseConstant;
import com.example.swimminggo.models.Distance;
import com.example.swimminggo.models.Style;
import com.example.swimminggo.singleton.CurrentDistance;
import com.example.swimminggo.singleton.CurrentStyle;
import com.example.swimminggo.singleton.UserProfile;
import com.example.swimminggo.view.Notification;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public class SwimmerChartFragment extends Fragment {

    View view;
    Spinner spnDistance, spnStyle;
    TabLayout tabLayout;
    ViewPager viewPager;
    PageAdapterSwimmer pageAdapter;
    TabItem tabMonth, tabQuarter, tabYear;
    ImageView imgViewNotification;
    Dialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_swimmer_chart, container, false);
        initComponent();
        initDatabase();
        action();
        return view;
    }

    private void initComponent(){

        spnDistance = view.findViewById(R.id.spn_distance);
        spnStyle = view.findViewById(R.id.spn_style);
        imgViewNotification = view.findViewById(R.id.img_view_notification);
    }

    private void initDatabase(){
        actionTabMenu();
        actionStyle();
        actionDistance();
        setDistance();
        setStyle();
    }

    private void action(){
        imgViewNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), Notification.class);
                intent.putExtra("swimmer_id", UserProfile.getInstance().currentUser.getId());
                startActivity(intent);
            }
        });
    }

    public void setDistance() {
        spnDistance.setAdapter(new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, ExerciseConstant.getInstance().getDistances()));
    }

    public void setStyle() {
        spnStyle.setAdapter(new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, ExerciseConstant.getInstance().getStyles()));
    }

    private void actionTabMenu() {
        tabLayout = view.findViewById(R.id.tablayout);
        tabMonth = view.findViewById(R.id.tab_month);
        tabQuarter = view.findViewById(R.id.tab_quarter);
        tabYear = view.findViewById(R.id.tab_year);
        viewPager = view.findViewById(R.id.viewPager);

        pageAdapter = new PageAdapterSwimmer(getChildFragmentManager(), tabLayout.getTabCount(), UserProfile.getInstance().currentUser.getId());
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setCurrentItem(0);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void actionDistance(){
        spnDistance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CurrentDistance.getInstance().setDistance(new Distance(Integer.parseInt(spnDistance.getSelectedItem().toString())));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                CurrentDistance.getInstance().setDistance(ExerciseConstant.getInstance().getDistances().get(0));
            }
        });
    }

    private void actionStyle(){
        spnStyle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Style style = Iterables.tryFind(ExerciseConstant.getInstance().getStyles(),
                        new Predicate<Style>() {
                            @Override
                            public boolean apply(@NullableDecl Style input) {
                                return input.getValue().equals(spnStyle.getSelectedItem().toString());
                            }
                        }).orNull();
                CurrentStyle.getInstance().setStyle(style);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                CurrentStyle.getInstance().setStyle(ExerciseConstant.getInstance().getStyles().get(0));
            }
        });
    }

}
