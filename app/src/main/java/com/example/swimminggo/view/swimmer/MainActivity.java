package com.example.swimminggo.view.swimmer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.swimminggo.R;
import com.example.swimminggo.view.coach.fragment.CalendarFragment;
import com.example.swimminggo.view.coach.fragment.LibraryCategories;
import com.example.swimminggo.view.coach.fragment.SettingFragment;
import com.example.swimminggo.view.swimmer.fragment.SwimmerChartFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Activity mainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_swimmer);
        this.mainActivity = this;
        initView();
    }

    private void initView() {
        bottomNavigationView = findViewById(R.id.bottom_bar_swimmer);
        loadFragment(new CalendarFragment(mainActivity));
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.item_workout:
                        loadFragment(new CalendarFragment(mainActivity));
                        return true;
                    case R.id.item_library:
                        loadFragment(new LibraryCategories());
                        return true;
                    case R.id.item_chart:
                        loadFragment(new SwimmerChartFragment());
                        return true;
                    case R.id.item_more:
                        loadFragment(new SettingFragment(mainActivity));
                        return true;
                }
                return false;
            }
        });
    }

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layout_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
