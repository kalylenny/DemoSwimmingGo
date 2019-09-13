package com.example.swimminggo.presenter.presenterImpl;

import com.example.swimminggo.models.Team;
import com.example.swimminggo.presenter.TeamPresenter;

import java.util.List;

public class TeamPresenterImpl implements TeamPresenter {

    @Override
    public List<Team> getListTeamByUserId(int userId) {
        return null;
    }

    @Override
    public Team getTeamById(int teamId) {
        return null;
    }

    @Override
    public int deleteTeam(int teamId) {
        return 0;
    }
    @Override
    public int updateTeam(int teamId, Team team) {
        return 0;
    }
}
