package com.example.swimminggo.view.coach.fragment;

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
import com.example.swimminggo.singleton.UserProfile;
import com.example.swimminggo.view.Login;
import com.example.swimminggo.view.profile.ChangePassword;
import com.example.swimminggo.view.profile.ViewProfile;


public class SettingFragment extends Fragment {
    View view;

    TextView txtFullName, txtRoleName;
    LinearLayout layoutViewProfile, layoutChangepassword, layoutLogout;

    User currentUser = UserProfile.getInstance().currentUser;

    public SettingFragment() {

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
                startActivity(new Intent(view.getContext(), Login.class));
            }
        });
    }

    private void initDatabase() {
        txtFullName.setText(currentUser.getFullName());
        txtRoleName.setText(currentUser.getRoleName());
    }
}
