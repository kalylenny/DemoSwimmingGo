package com.example.swimminggo.presenter.presenterImpl;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.swimminggo.constant.URLConstant;
import com.example.swimminggo.models.Time;
import com.example.swimminggo.presenter.ChartPresenter;
import com.example.swimminggo.singleton.UserProfile;
import com.example.swimminggo.view.swimmer.fragment.SwimmerMonthChartFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChartPresenterImpl implements ChartPresenter {

    SwimmerMonthChartFragment swimmerMonthChartFragment;

    public ChartPresenterImpl(SwimmerMonthChartFragment swimmerMonthChartFragment) {
        this.swimmerMonthChartFragment = swimmerMonthChartFragment;
        AndroidNetworking.initialize(swimmerMonthChartFragment.getContext());
    }

    @Override
    public void onGetDataByMonth(String styleId, int distance, int swimmerId, int month, int year) {
        AndroidNetworking.post(URLConstant.getInstance().URL_GET_CHART_BY_MONTH)
                .addJSONObjectBody(toJSONObject(styleId, distance, swimmerId, month, year))
                .addHeaders("Authorization", "Bearer " + UserProfile.getInstance().accessToken)
                .build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getBoolean("success")){
                        JSONArray jsonArray =response.getJSONArray("chart");
                        List<Integer> yAxisData = new ArrayList<>(jsonArray.length());
                        for(int i = 0; i < jsonArray.length(); i++){
                            Time time = new Time(jsonArray.getJSONObject(i));
                            yAxisData.add(time.getNumOfWeek()-1, time.toMillisec());
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

    private JSONObject toJSONObject(String styleId, int distance, int swimmerId, int month, int year) {
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
