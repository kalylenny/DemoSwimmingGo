package com.example.swimminggo.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.swimminggo.R;
import com.example.swimminggo.adapter.PageAdapterSwimmer;
import com.example.swimminggo.constant.ExerciseConstant;
import com.example.swimminggo.models.Distance;
import com.example.swimminggo.models.Style;
import com.example.swimminggo.models.Swimmer;
import com.example.swimminggo.singleton.CurrentDistance;
import com.example.swimminggo.singleton.CurrentStyle;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public class SwimmerProfile extends AppCompatActivity {
    TextView txtFullName, txtEmail, txtPhone, txtDob, txtGender;
    Spinner spnDistance, spnStyle;
    Swimmer swimmer;
    TabLayout tabLayout;
    ViewPager viewPager;
    PageAdapterSwimmer pageAdapter;
    TabItem tabMonth, tabQuarter, tabYear;
    ImageView imageViewNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swimmer_profile);
        initComponent();
        initDatabase();
        actionDistance();
        actionStyle();
        actionTabMenu();
        action();
    }

    private void initDatabase() {
        swimmer = (Swimmer) getIntent().getSerializableExtra("swimmer");
        txtFullName.setText(swimmer.getFullName());
        txtEmail.setText(swimmer.getEmail());
        txtPhone.setText(swimmer.getPhone());
        txtGender.setText((swimmer.getGender() == 1) ? "Nam" : "Nữ");
        txtDob.setText(swimmer.getDob());
        setDistance();
        setStyle();
    }

    private void action(){
        imageViewNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SwimmerProfile.this, Notification.class);
                intent.putExtra("swimmer_id", swimmer.getId());
                startActivity(intent);
            }
        });
    }

    private void actionTabMenu() {
        tabLayout = findViewById(R.id.tablayout);
        tabMonth = findViewById(R.id.tab_month);
        tabQuarter = findViewById(R.id.tab_quarter);
        tabYear = findViewById(R.id.tab_year);
        viewPager = findViewById(R.id.viewPager);

        pageAdapter = new PageAdapterSwimmer(getSupportFragmentManager(), tabLayout.getTabCount(), swimmer.getId());
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

    public void setDistance() {
        spnDistance.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, ExerciseConstant.getInstance().getDistances()));
    }

    public void setStyle() {
        spnStyle.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, ExerciseConstant.getInstance().getStyles()));
    }

    private void initComponent() {
        txtFullName = findViewById(R.id.txt_fullname);
        txtDob = findViewById(R.id.txt_dob);
        txtPhone = findViewById(R.id.txt_phone);
        txtGender = findViewById(R.id.txt_gender);
        txtEmail = findViewById(R.id.txt_email);

        spnDistance = findViewById(R.id.spn_distance);
        spnStyle = findViewById(R.id.spn_style);
        imageViewNotification = findViewById(R.id.img_view_notification);
    }
}
