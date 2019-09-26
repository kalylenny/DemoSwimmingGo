package com.example.swimminggo.presenter;

import com.example.swimminggo.models.Team;

import java.util.List;

public interface TeamPresenter {
    void getListTeam();
    Team getTeamById(int teamId);
    int deleteTeam(int teamId);
    int updateTeam(int teamId, Team team);
}
