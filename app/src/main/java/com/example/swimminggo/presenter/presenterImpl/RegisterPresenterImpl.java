package com.example.swimminggo.presenter.presenterImpl;

import com.androidnetworking.AndroidNetworking;
import com.example.swimminggo.presenter.RegisterPresenter;
import com.example.swimminggo.view.Register;

public class RegisterPresenterImpl implements RegisterPresenter {

    Register register;

    public RegisterPresenterImpl(Register register){
        this.register = register;
        AndroidNetworking.initialize(register.getApplicationContext());
    }

    @Override
    public void onRegister(String username, String password) {

    }


}
