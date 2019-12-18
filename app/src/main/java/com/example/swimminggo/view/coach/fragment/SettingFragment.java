package com.example.swimminggo.view.coach.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.swimminggo.R;
import com.example.swimminggo.models.User;
import com.example.swimminggo.singleton.ListCheckBoxSwimmer;
import com.example.swimminggo.singleton.ListExercise;
import com.example.swimminggo.singleton.ListExerciseByPhase;
import com.example.swimminggo.singleton.ListSwimmer;
import com.example.swimminggo.singleton.ListTeam;
import com.example.swimminggo.singleton.Position;
import com.example.swimminggo.singleton.UserProfile;
import com.example.swimminggo.singleton.Videos;
import com.example.swimminggo.view.profile.ChangePassword;
import com.example.swimminggo.view.profile.ViewProfile;


public class SettingFragment extends Fragment {
    View view;

    TextView txtFullName, txtRoleName;
    LinearLayout layoutViewProfile, layoutChangepassword, layoutLogout;

    Activity mainActivity;

    User currentUser = UserProfile.getInstance().currentUser;

    public SettingFragment(Activity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_setting, container, false);
        initComponent();
        initDatabase();
        action();

        return view;
    }

    private void initComponent() {
        txtFullName = view.findViewById(R.id.txt_fullname);
        txtRoleName = view.findViewById(R.id.txt_rolename);

        layoutViewProfile = view.findViewById(R.id.layout_view_profile);
        layoutChangepassword = view.findViewById(R.id.layout_change_password);
        layoutLogout = view.findViewById(R.id.layout_logout);
    }

    private void action() {
        layoutViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), ViewProfile.class));
            }
        });

        layoutChangepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), ChangePassword.class));
            }
        });

        layoutLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearData();
                mainActivity.finish();
//                if (EditProfile.editProfile != null)
//                    EditProfile.editProfile.finish();
            }
        });
    }

    private void initDatabase() {
        txtFullName.setText(currentUser.getFullName());
        txtRoleName.setText(currentUser.getRoleName());
    }

    private void clearData() {
        UserProfile.getInstance().clearData();
//        if (ExerciseConstant.getInstance() != null)
//            ExerciseConstant.getInstance().clearData();
        if (ListCheckBoxSwimmer.getInstance() != null)
            ListCheckBoxSwimmer.getInstance().clearData();
        if (ListExercise.getInstance() != null)
            ListExercise.getInstance().clearData();
        if (ListExerciseByPhase.getInstance() != null)
            ListExerciseByPhase.getInstance().clearData();
        if (ListSwimmer.getInstance() != null)
            ListSwimmer.getInstance().clearData();
        if (ListTeam.getInstance() != null)
            ListTeam.getInstance().clearData();
        if (Position.getInstance() != null)
            Position.getInstance().clearData();
        if (Videos.getInstance() != null)
            Videos.getInstance().clearData();
    }
}
