package com.example.swimminggo.presenter.presenterImpl;

import android.os.Handler;
import android.os.Looper;

import com.example.swimminggo.models.User;
import com.example.swimminggo.presenter.UserPresenter;
import com.example.swimminggo.view.Login;

public class UserPresenterImpl implements UserPresenter {
    Login loginView;
    Handler handler;

    public UserPresenterImpl(Login loginView){
        this.loginView = loginView;
        handler = new Handler(Looper.getMainLooper());
    }
    @Override
    public void onLogin(String username, String password) {
        loginView.onLoginResult(true);
    }

    @Override
    public int register(User user) {
        return 0;
    }
}
