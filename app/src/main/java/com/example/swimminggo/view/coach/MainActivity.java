package com.example.swimminggo.view.coach;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.swimminggo.R;
import com.example.swimminggo.constant.ExerciseConstant;
import com.example.swimminggo.presenter.ExercisePresenter;
import com.example.swimminggo.presenter.TeamPresenter;
import com.example.swimminggo.presenter.presenterImpl.ExercisePresenterImpl;
import com.example.swimminggo.presenter.presenterImpl.TeamPresenterImpl;
import com.example.swimminggo.singleton.ListTeam;
import com.example.swimminggo.view.coach.fragment.CalendarFragment;
import com.example.swimminggo.view.coach.fragment.LibraryCategories;
import com.example.swimminggo.view.coach.fragment.SettingFragment;
import com.example.swimminggo.view.coach.fragment.TeamFragment;
import com.example.swimminggo.view.coach.fragment.WorkoutFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Activity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_coach);
        this.mainActivity = this;
        initDatabase();
        initView();
    }

    private void initDatabase() {
        ExerciseConstant.newInstance();
        initListTeams();
        initAges();
        initDistances();
        initStyles();
        initPhases();
    }

    private void initStyles() {
        ExercisePresenter exercisePresenter = new ExercisePresenterImpl(this);
        exercisePresenter.onGetStyles();
    }

    private void initDistances() {
        ExercisePresenter exercisePresenter = new ExercisePresenterImpl(this);
        exercisePresenter.onGetDistances();
    }

    private void initPhases(){
        ExercisePresenter exercisePresenter = new ExercisePresenterImpl(this);
        exercisePresenter.onGetPhases();
    }

    private void initAges() {

    }

    private void initListTeams() {
        TeamPresenter teamPresenter = new TeamPresenterImpl(this);
        ListTeam.newInstance();
        teamPresenter.getListTeam();
    }

    public void initView(){
        bottomNavigationView = findViewById(R.id.bottom_bar);
        loadFragment(new WorkoutFragment());
        loadFragment(new CalendarFragment(mainActivity));
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.item_team:
                        loadFragment(new TeamFragment());
                        return true;
                    case R.id.item_calendar:
                        loadFragment(new CalendarFragment(mainActivity));
                        return true;
                    case R.id.item_setting:
                        loadFragment(new SettingFragment(mainActivity));
                        return true;
                    case R.id.item_workout:
                        loadFragment(new WorkoutFragment());
                        return true;
                    case R.id.item_library:
                        loadFragment(new LibraryCategories());
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
