package com.example.swimminggo.presenter;

import com.example.swimminggo.models.Team;

import java.util.List;

public interface TeamPresenter {
    void getListTeam();
    Team getTeamById(int position);
    void deleteTeam(int position);
    void updateTeam(int position, Team team);
    void onAddTeam(Team team);
}
