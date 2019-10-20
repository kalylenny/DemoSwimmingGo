package com.example.swimminggo.view.coach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.swimminggo.R;
import com.example.swimminggo.view.coach.fragment.ChartFragment;
import com.example.swimminggo.view.coach.fragment.LibraryFragment;
import com.example.swimminggo.view.coach.fragment.SettingFragment;
import com.example.swimminggo.view.coach.fragment.TeamFragment;
import com.example.swimminggo.view.coach.fragment.WorkoutFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_coach);
        initView();
    }
    public void initView(){
        bottomNavigationView = findViewById(R.id.bottom_bar);
        loadFragment(new WorkoutFragment());
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.item_team:
                        loadFragment(new TeamFragment());
                        return true;
                    case R.id.item_chart:
                        loadFragment(new ChartFragment());
                        return true;
                    case R.id.item_setting:
                        loadFragment(new SettingFragment());
                        return true;
                    case R.id.item_workout:
                        loadFragment(new WorkoutFragment());
                        return true;
                    case R.id.item_library:
                        loadFragment(new LibraryFragment());
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
