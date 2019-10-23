package com.example.swimminggo.singleton;

public class ListLesson {
    private static final ListLesson ourInstance = new ListLesson();

    public static ListLesson getInstance() {
        return ourInstance;
    }

    private ListLesson() {
    }
}
