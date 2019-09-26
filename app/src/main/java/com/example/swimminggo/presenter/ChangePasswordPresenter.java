package com.example.swimminggo.presenter;

public interface ChangePasswordPresenter {
    void onvalidatePassword(String password, String rePassword);
    void onSubmit(String oldPassword, String newPassword);
}
