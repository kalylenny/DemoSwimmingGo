package com.example.swimminggo.presenter;

import java.util.List;

public interface SwimmerPresenter {
    void onGetListSwimmerByTeamId(int teamId);
    void onRemoveSwimmersFromTeam(int teamId, List<Integer> positions);
}
