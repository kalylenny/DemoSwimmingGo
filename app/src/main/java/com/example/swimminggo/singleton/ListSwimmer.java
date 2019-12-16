package com.example.swimminggo.singleton;

import com.example.swimminggo.models.Swimmer;

import java.util.ArrayList;
import java.util.List;

public class ListSwimmer {
    public List<Swimmer> swimmers;
    public List<Boolean> isCheckeds;
    private static ListSwimmer ourInstance;

    public static ListSwimmer getInstance() {
        if (ourInstance == null)
            ourInstance = new ListSwimmer();
        return ourInstance;
    }

    public static void newInstance(){
        ourInstance = new ListSwimmer();
    }

    private ListSwimmer() {
        swimmers = new ArrayList<>();
        isCheckeds = new ArrayList<>();
    }

    public List<Swimmer> getSwimmers(){
        return swimmers;
    }
    public void clearData() {
        ourInstance = null;
    }
}
