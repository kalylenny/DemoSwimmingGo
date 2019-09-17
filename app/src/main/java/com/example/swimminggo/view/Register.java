package com.example.swimminggo.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.swimminggo.R;
import com.example.swimminggo.presenter.RegisterPresenter;
import com.example.swimminggo.presenter.presenterImpl.RegisterPresenterImpl;

public class Register extends AppCompatActivity {

    RegisterPresenter registerPresenter;
    Button btnRegister;
    EditText edtUsername, edtPassword, edtRepassword, edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initComponent();
        action();
    }

    private void initComponent() {
        registerPresenter = new RegisterPresenterImpl(this);

        btnRegister = findViewById(R.id.btn_register);

        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        edtRepassword = findViewById(R.id.edt_repassword);
        edtEmail = findViewById(R.id.edt_email);
    }

    private void action() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerPresenter.validateForm(edtUsername.getText().toString(),
                        edtPassword.getText().toString(),
                        edtRepassword.getText().toString(),
                        edtEmail.getText().toString());
            }
        });
    }

    public void doRegister(boolean result, String message) {
        if (result) {
            startActivity(new Intent(Register.this, Login.class));
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
}
