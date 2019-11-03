package com.example.swimminggo.singleton;

import com.example.swimminggo.models.Record;

import java.util.ArrayList;

public class TotalRecord {
    private static TotalRecord ourInstance;
    private ArrayList<ArrayList<Record>> totalRecord;
    public static TotalRecord getInstance() {
        return ourInstance;
    }

    public static void newInstance(){
        ourInstance = new TotalRecord();
    }

    private TotalRecord() {
        totalRecord = new ArrayList<>();
    }

    public ArrayList<ArrayList<Record>> getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(ArrayList<ArrayList<Record>> totalRecord) {
        this.totalRecord = totalRecord;
    }
}
