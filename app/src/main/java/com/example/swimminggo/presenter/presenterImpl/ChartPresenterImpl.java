package com.example.swimminggo.presenter.presenterImpl;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.swimminggo.constant.URLConstant;
import com.example.swimminggo.models.Time;
import com.example.swimminggo.presenter.ChartPresenter;
import com.example.swimminggo.singleton.UserProfile;
import com.example.swimminggo.view.swimmer.fragment.SwimmerMonthChartFragment;
import com.example.swimminggo.view.swimmer.fragment.SwimmerQuarterChartFragment;
import com.example.swimminggo.view.swimmer.fragment.SwimmerYearChartFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChartPresenterImpl implements ChartPresenter {

    SwimmerMonthChartFragment swimmerMonthChartFragment;
    SwimmerQuarterChartFragment swimmerQuarterChartFragment;
    SwimmerYearChartFragment swimmerYearChartFragment;

    public ChartPresenterImpl(SwimmerMonthChartFragment swimmerMonthChartFragment) {
        this.swimmerMonthChartFragment = swimmerMonthChartFragment;
        AndroidNetworking.initialize(swimmerMonthChartFragment.getContext());
    }

    public ChartPresenterImpl(SwimmerQuarterChartFragment swimmerQuarterChartFragment) {
        this.swimmerQuarterChartFragment = swimmerQuarterChartFragment;
        AndroidNetworking.initialize(swimmerQuarterChartFragment.getContext());
    }

    public ChartPresenterImpl(SwimmerYearChartFragment swimmerYearChartFragment) {
        this.swimmerYearChartFragment = swimmerYearChartFragment;
        AndroidNetworking.initialize(swimmerYearChartFragment.getContext());
    }

    @Override
    public void onGetDataByMonth(String styleId, int distance, int swimmerId, int month, int year) {
        AndroidNetworking.post(URLConstant.getInstance().URL_GET_CHART_BY_MONTH)
                .addJSONObjectBody(toMonthJSONObject(styleId, distance, swimmerId, month, year))
                .addHeaders("Authorization", "Bearer " + UserProfile.getInstance().accessToken)
                .build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getBoolean("success")) {
                        JSONArray jsonArray = response.getJSONArray("chart");
                        List<Float> yAxisData = new ArrayList<>(jsonArray.length());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Time time = new Time(jsonArray.getJSONObject(i));
                            yAxisData.add(time.getNumOfWeek() - 1, time.toSecond());
                        }
                        List<String> axisData = new ArrayList<>(Arrays.asList("Tuần 1", "Tuần 2", "Tuần 3", "Tuần 4"));
                        swimmerMonthChartFragment.setupLineChart(axisData, yAxisData);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ANError anError) {

            }
        });
    }

    @Override
    public void onGetDataByQuarter(String styleId, int distance, int swimmerId, int quarter, int year) {
        AndroidNetworking.post(URLConstant.getInstance().URL_GET_CHART_BY_QUARTER)
                .addJSONObjectBody(toQuarterJSONObject(styleId, distance, swimmerId, quarter, year))
                .addHeaders("Authorization", "Bearer " + UserProfile.getInstance().accessToken)
                .build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getBoolean("success")) {
                        JSONArray jsonArray = response.getJSONArray("chart");
                        List<Float> yAxisData = new ArrayList<>(jsonArray.length());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Time time = new Time(jsonArray.getJSONObject(i));
                            if (time.getMonth() % 3 != 0)
                                yAxisData.add(time.getMonth()%3 - 1, time.toSecond());
                            else
                                yAxisData.add(2, time.toSecond());
                        }
                        String month1 = "Tháng " + ((quarter - 1) * 3 + 1);
                        String month2 = "Tháng " + ((quarter - 1) * 3 + 2);
                        String month3 = "Tháng " + ((quarter - 1) * 3 + 3);
                        List<String> axisData = new ArrayList<String>(Arrays.asList(month1, month2, month3));
                        swimmerQuarterChartFragment.setupLineChart(axisData, yAxisData);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ANError anError) {

            }
        });
    }

    @Override
    public void onGetDataByYear(String styleId, int distance, int swimmerId, int year) {
        AndroidNetworking.post(URLConstant.getInstance().URL_GET_CHART_BY_YEAR)
                .addJSONObjectBody(toYearJSONObject(styleId, distance, swimmerId, year))
                .addHeaders("Authorization", "Bearer " + UserProfile.getInstance().accessToken)
                .build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getBoolean("success")) {
                        JSONArray jsonArray = response.getJSONArray("chart");
                        List<Float> yAxisData = new ArrayList<>(jsonArray.length());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Time time = new Time(jsonArray.getJSONObject(i));
                            yAxisData.add(time.getQuarter() - 1, time.toSecond());
                        }
                        List<String> axisData = new ArrayList<String>(Arrays.asList("Quý 1", "Quý 2", "Quý 3", "Quý 4"));
                        swimmerYearChartFragment.setupLineChart(axisData, yAxisData);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ANError anError) {

            }
        });
    }

    private JSONObject toYearJSONObject(String styleId, int distance, int swimmerId, int year) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("swim_style", styleId);
            jsonObject.put("distance", distance);
            jsonObject.put("swimmer_id", swimmerId);
            jsonObject.put("year", year);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private JSONObject toQuarterJSONObject(String styleId, int distance, int swimmerId, int quarter, int year) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("swim_style", styleId);
            jsonObject.put("distance", distance);
            jsonObject.put("swimmer_id", swimmerId);
            jsonObject.put("quarter", quarter);
            jsonObject.put("year", year);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private JSONObject toMonthJSONObject(String styleId, int distance, int swimmerId, int month, int year) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("swim_style", styleId);
            jsonObject.put("distance", distance);
            jsonObject.put("swimmer_id", swimmerId);
            jsonObject.put("month", month);
            jsonObject.put("year", year);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
