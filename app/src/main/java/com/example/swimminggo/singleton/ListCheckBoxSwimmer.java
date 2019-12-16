package com.example.swimminggo.singleton;

import java.util.ArrayList;
import java.util.List;

public class ListCheckBoxSwimmer {
    private static ListCheckBoxSwimmer ourInstance;
    public List<Boolean> checkSwimmers;

    public static ListCheckBoxSwimmer getInstance() {
        return ourInstance;
    }

    public static void newInstance(){
        ourInstance = new ListCheckBoxSwimmer();
    }

    private ListCheckBoxSwimmer() {
        checkSwimmers = new ArrayList<>();
    }

    public void clearData() {
        ourInstance = null;
    }
}
