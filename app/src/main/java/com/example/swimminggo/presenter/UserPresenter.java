package com.example.swimminggo.presenter;

import com.example.swimminggo.models.User;

public interface UserPresenter {
    void onLogin(String username, String password);
    int register(User user);
}
