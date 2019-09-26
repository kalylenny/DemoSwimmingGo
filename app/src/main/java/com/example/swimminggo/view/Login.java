package com.example.swimminggo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swimminggo.R;
import com.example.swimminggo.presenter.LoginPresenter;
import com.example.swimminggo.presenter.presenterImpl.LoginPresenterImpl;
import com.example.swimminggo.singleton.UserProfile;
import com.example.swimminggo.view.coach.MainActivity;
import com.example.swimminggo.view.profile.EditProfile;

public class Login extends AppCompatActivity {

    private LoginPresenter loginPresenter;
    private TextView txtRegister;
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
        loginPresenter = new LoginPresenterImpl(this);

        btnLogin = findViewById(R.id.btn_login);

        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);

        txtRegister = findViewById(R.id.txt_register);

    }

    private void action() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.onLogin(edtUsername.getText().toString(), edtPassword.getText().toString());
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
    }

    public void onLoginResult(Boolean result, String message) {
        if (result) {
            if (UserProfile.getInstance().currentUser.getIsVerified() == 1) {
                startActivity(new Intent(Login.this, MainActivity.class));
            } else {
                startActivity(new Intent(Login.this, EditProfile.class));
            }
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
}
