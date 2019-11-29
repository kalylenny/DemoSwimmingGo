package com.example.swimminggo.singleton;

import com.example.swimminggo.models.Record;

import java.util.ArrayList;

public class Records {
    private static Records ourInstance;
    private ArrayList<Record> records;

    public static Records getInstance() {
        return ourInstance;
    }

    public static void newInstance() {
        ourInstance = new Records();
    }

    private Records() {
        records = new ArrayList<>();
    }

    public ArrayList<Record> getRecords() {
        return records;
    }
}
