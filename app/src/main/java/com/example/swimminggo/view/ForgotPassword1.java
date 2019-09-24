package com.example.swimminggo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.swimminggo.R;
import com.example.swimminggo.presenter.ForgotPasswordPresenter;
import com.example.swimminggo.presenter.presenterImpl.ForgotPasswordPresenterImpl;

public class ForgotPassword1 extends AppCompatActivity {

    EditText edtEmail;
    Button btnConfirm;

    ForgotPasswordPresenter forgotPasswordPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password1);
        initComponent();
        action();
    }

    private void initComponent(){
        forgotPasswordPresenter = new ForgotPasswordPresenterImpl(this);

        edtEmail = findViewById(R.id.edt_email);

        btnConfirm = findViewById(R.id.btn_confirm);

    }

    private void action(){
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotPasswordPresenter.onValidateMail(edtEmail.getText().toString());
            }
        });
    }

    public void doSendMail(boolean result){
        if (result){
            startActivity(new Intent(ForgotPassword1.this, ForgotPassword2.class));
        } else {

        }
    }

    public void doValidateMail(boolean result){
        if (result){
            forgotPasswordPresenter.onSendMail(edtEmail.getText().toString());
        } else {
            Toast.makeText(this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
        }
    }


}
