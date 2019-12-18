package com.example.swimminggo.view.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.swimminggo.R;
import com.example.swimminggo.models.User;
import com.example.swimminggo.singleton.UserProfile;

public class ViewProfile extends AppCompatActivity {

    User currentUser = UserProfile.getInstance().currentUser;

    TextView txtFullName, txtDob, txtPhone, txtGender, txtEmail;
    ImageView imgEdit;
    public static Activity viewProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        initComponent();
        initDatabase();
        action();
    }

    private void initComponent(){
        viewProfile = this;

        txtFullName = findViewById(R.id.txt_fullname);
        txtDob = findViewById(R.id.txt_dob);
        txtPhone = findViewById(R.id.txt_phone);
        txtGender = findViewById(R.id.txt_gender);
        txtEmail = findViewById(R.id.txt_email);

        imgEdit = findViewById(R.id.img_edit);
    }

    private void initDatabase(){
        txtFullName.setText(currentUser.getFullName());
        txtEmail.setText(currentUser.getEmail());
        txtPhone.setText(currentUser.getPhone());
        txtGender.setText((currentUser.getGender() == 1)?"Nam":"Nữ");
        txtDob.setText(currentUser.getDob());
    }

    private void action(){
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewProfile.this, EditProfile.class));
            }
        });
    }
}
