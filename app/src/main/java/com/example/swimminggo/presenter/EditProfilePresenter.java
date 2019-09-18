package com.example.swimminggo.presenter;

import com.example.swimminggo.models.User;

public interface EditProfilePresenter {
    public void onValidateForm(User dataUser);
    public void onEdit(User dataUser);
}
