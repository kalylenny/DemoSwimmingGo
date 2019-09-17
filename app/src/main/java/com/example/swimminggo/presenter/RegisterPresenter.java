package com.example.swimminggo.presenter;

public interface RegisterPresenter {
    public void onRegister(String username, String password, String email);
    public void validateForm(String username, String password, String rePassword, String email);
}
