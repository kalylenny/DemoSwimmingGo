package com.example.swimminggo.singleton;

public class Position {
    private static Position ourInstance;
    private int lastPosition;

    public static Position getInstance() {
        if (ourInstance == null)
            ourInstance = new Position();
        return ourInstance;
    }

    private Position() {
        lastPosition = -1;
    }

    public int getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(int lastPosition) {
        this.lastPosition = lastPosition;
    }
}
