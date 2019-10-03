package com.example.swimminggo.presenter;

import java.util.List;

import com.example.swimminggo.models.Account;
import com.example.swimminggo.models.Swimmer;

import java.util.List;

public interface SwimmerPresenter {

    void onGetListSwimmerByTeamId(int teamId);
    void onRemoveSwimmersFromTeam(int teamId, List<Integer> positions);
    void onGetListNewSwimmer(int number);
    void onGetListSwimmerNoTeam();
    void onAddNewSwimmer(List<Account> accounts);
    void onAddSwimmerToTeam(List<Swimmer> swimmers);
}
