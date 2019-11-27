package com.example.swimminggo.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.swimminggo.R;
import com.example.swimminggo.presenter.ForgotPasswordPresenter2;
import com.example.swimminggo.presenter.presenterImpl.ForgotPasswordPresenterImpl2;

public class ForgotPassword2 extends AppCompatActivity {

    ForgotPasswordPresenter2 forgotPasswordPresenter2;

    EditText edtOtp;
    Button btnConfirm, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password2);
        initComponent();
        action();
    }

    private void initComponent() {
        forgotPasswordPresenter2 = new ForgotPasswordPresenterImpl2(this);

        edtOtp = findViewById(R.id.edt_key);

        btnConfirm = findViewById(R.id.btn_confirm);
        btnBack = findViewById(R.id.btn_back);
    }

    private void action() {
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotPasswordPresenter2.onSendOtp(edtOtp.getText().toString());
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotPassword2.this, ForgotPassword1.class));
            }
        });
    }

    public void doSendOtp(Boolean result, String otp) {
        if (result) {
            Intent intent = new Intent(ForgotPassword2.this, ForgotPassword3.class);
            intent.putExtra("otp", otp);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Mã OTP không chính xác", Toast.LENGTH_SHORT).show();
        }
    }
}
