package com.example.swimminggo.view.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.swimminggo.R;
import com.example.swimminggo.presenter.ChangePasswordPresenter;
import com.example.swimminggo.presenter.presenterImpl.ChangePasswordPresenterImpl;
import com.example.swimminggo.view.coach.MainActivity;

public class ChangePassword extends AppCompatActivity {

    ChangePasswordPresenter changePasswordPresenter;
    EditText edtOldPassword, edtNewPassWord, edtReNewPassword;
    Button btnConfirm, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initComponent();
        action();
    }

    private void initComponent() {
        changePasswordPresenter = new ChangePasswordPresenterImpl(this);

        edtOldPassword = findViewById(R.id.edt_old_password);
        edtNewPassWord = findViewById(R.id.edt_new_password);
        edtReNewPassword = findViewById(R.id.edt_renew_password);

        btnConfirm = findViewById(R.id.btn_confirm);
        btnBack = findViewById(R.id.btn_back);
    }

    private void action() {
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePasswordPresenter.onvalidatePassword(edtNewPassWord.getText().toString(), edtReNewPassword.getText().toString());
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChangePassword.this, MainActivity.class));
            }
        });
    }

    public void doValidatePassword(boolean result, String message) {
        if (result) {
            changePasswordPresenter.onSubmit(edtOldPassword.getText().toString(), edtNewPassWord.getText().toString());
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    public void doSubmit(boolean result, String message){
        if (result) {
            startActivity(new Intent(ChangePassword.this, MainActivity.class));
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
}
