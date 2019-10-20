package com.example.swimminggo.presenter;

import com.example.swimminggo.models.Account;
import com.example.swimminggo.models.Swimmer;

import java.util.List;

public interface SwimmerPresenter {

    void onGetListSwimmerByTeamId(int teamId);
    void onGetListNewSwimmer(int number);
    void onGetListSwimmerNoTeam();
    void onAddNewSwimmer(List<Account> accounts);
    void onAddSwimmerToTeam(List<Swimmer> swimmers);
}
