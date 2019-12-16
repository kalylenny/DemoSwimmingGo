package com.example.swimminggo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swimminggo.R;
import com.example.swimminggo.constant.ExerciseConstant;
import com.example.swimminggo.presenter.ExercisePresenter;
import com.example.swimminggo.presenter.LoginPresenter;
import com.example.swimminggo.presenter.presenterImpl.ExercisePresenterImpl;
import com.example.swimminggo.presenter.presenterImpl.LoginPresenterImpl;
import com.example.swimminggo.singleton.UserProfile;
import com.example.swimminggo.view.coach.MainActivity;
import com.example.swimminggo.view.profile.EditProfile;

public class Login extends AppCompatActivity {

    private LoginPresenter loginPresenter;
    private TextView txtRegister, txtForgotPassword;
    EditText edtUsername, edtPassword;
    Button btnLogin;
    public ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponent();
        initDatabase();
        action();
    }

    private void initComponent() {
        loginPresenter = new LoginPresenterImpl(this);

        progressDialog = new ProgressDialog(this);
        btnLogin = findViewById(R.id.btn_login);

        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);

        txtRegister = findViewById(R.id.txt_register);
        txtForgotPassword = findViewById(R.id.txt_forgot_password);
    }

    private void initDatabase(){
        progressDialog.setMessage("Vui lòng chờ");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMax(100);
        ExerciseConstant.newInstance();
        initStyles();
        initDistances();
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

    private void action() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.onLogin(edtUsername.getText().toString(), edtPassword.getText().toString());
                progressDialog.show();
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, ForgotPassword1.class));
            }
        });
    }

    public void onLoginResult(Boolean result, String message) {
        if (result) {
            if (UserProfile.getInstance().currentUser.getIsVerified() == 1) {
                if (UserProfile.getInstance().currentUser.getRoleName().equals("coach"))
                    startActivity(new Intent(Login.this, MainActivity.class));
                else
                    startActivity(new Intent(Login.this, com.example.swimminggo.view.swimmer.MainActivity.class));
            } else {
                startActivity(new Intent(Login.this, EditProfile.class));
            }
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
}
