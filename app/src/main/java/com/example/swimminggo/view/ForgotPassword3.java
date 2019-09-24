package com.example.swimminggo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.swimminggo.R;
import com.example.swimminggo.presenter.ForgotPasswordPresenter3;
import com.example.swimminggo.presenter.presenterImpl.ForgotPasswordPresenterImpl3;

public class ForgotPassword3 extends AppCompatActivity {

    ForgotPasswordPresenter3 forgotPasswordPresenter3;

    EditText edtPassword, edtRePassword;

    Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password3);
        initComponent();
        action();
    }

    private void initComponent() {
        forgotPasswordPresenter3 = new ForgotPasswordPresenterImpl3(this);

        edtPassword = findViewById(R.id.edt_password);
        edtRePassword = findViewById(R.id.edt_repassword);

        btnConfirm = findViewById(R.id.btn_confirm);
    }

    private void action() {
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotPasswordPresenter3.onValidatePassword(edtPassword.getText().toString(), edtRePassword.getText().toString());
            }
        });
    }

    public void doValidatePassword(boolean result, String message) {
        if (result) {
            String otp = getIntent().getStringExtra("otp");
            forgotPasswordPresenter3.onSubmit(edtPassword.getText().toString(), otp);
        }
    }

    public void doSubmit(boolean result, String message) {
        if (result) {
            startActivity(new Intent(ForgotPassword3.this, Login.class));
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
}
