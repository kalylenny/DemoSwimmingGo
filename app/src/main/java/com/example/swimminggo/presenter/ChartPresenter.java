package com.example.swimminggo.presenter;

public interface ChartPresenter {
    void onGetDataByMonth(String styleId, int distance, int swimmerId, int month, int year);
    void onGetDataByQuarter(String styleId, int distance, int swimmerId, int quarter, int year);
    void onGetDataByYear(String styleId, int distance, int swimmerId, int year);
}
