package com.example.swimminggo.presenter;

import com.example.swimminggo.models.Account;

import java.util.List;

public interface SwimmerPresenter {

    void onGetListSwimmerByTeamId(int teamId);
    void onGetListNewSwimmer(int number);
    void onAddNewSwimmer(List<Account> accounts);
}
