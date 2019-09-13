package com.example.swimminggo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.swimminggo.R;
import com.example.swimminggo.models.User;
import com.example.swimminggo.presenter.UserPresenter;
import com.example.swimminggo.presenter.presenterImpl.UserPresenterImpl;
import com.example.swimminggo.view.coach.MainActivity;

public class Login extends AppCompatActivity {

    private UserPresenter userPresenter;
    EditText edtUsername, edtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponent();
        action();
    }

    private void initComponent() {
        btnLogin = findViewById(R.id.btn_login);
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        userPresenter = new UserPresenterImpl(this);
    }

    private void action() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userPresenter.onLogin(edtUsername.getText().toString(), edtPassword.getText().toString());
            }
        });
    }

    public void onLoginResult(Boolean result){
        if (result){
            startActivity(new Intent(Login.this, MainActivity.class));
        }
    }
}
