package com.example.swimminggo.singleton;

import com.example.swimminggo.models.Team;

import java.util.ArrayList;
import java.util.List;

public class ListTeam {
    private List<Team> listTeam;
    private static ListTeam ourInstance;

    public static ListTeam getInstance() {
        return ourInstance;
    }

    public static void newInstance(){
        ourInstance = new ListTeam();
    }

    private ListTeam() {
        listTeam = new ArrayList<>();
    }

    public List<Team> getListTeam() {
        return listTeam;
    }

    public void clearData() {
        ourInstance = null;
    }
}
