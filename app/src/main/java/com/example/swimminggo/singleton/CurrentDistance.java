package com.example.swimminggo.singleton;

import com.example.swimminggo.models.Distance;

public class CurrentDistance {
    private Distance distance;
    private static CurrentDistance ourInstance;

    public static CurrentDistance getInstance() {
        if (ourInstance == null)
            ourInstance = new CurrentDistance();
        return ourInstance;
    }

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    private CurrentDistance() {

    }
}
