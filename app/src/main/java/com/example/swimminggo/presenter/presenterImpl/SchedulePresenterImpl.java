package com.example.swimminggo.presenter.presenterImpl;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.swimminggo.constant.URLConstant;
import com.example.swimminggo.models.Date;
import com.example.swimminggo.models.LessonPlan;
import com.example.swimminggo.presenter.SchedulePresenter;
import com.example.swimminggo.singleton.UserProfile;
import com.example.swimminggo.utils.DateUtils;
import com.example.swimminggo.view.coach.fragment.CalendarFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SchedulePresenterImpl implements SchedulePresenter {

    private CalendarFragment calendarFragment;

    public SchedulePresenterImpl(CalendarFragment calendarFragment) {
        this.calendarFragment = calendarFragment;
        AndroidNetworking.initialize(calendarFragment.getContext());
    }

    @Override
    public void loadListLessonPlanByDate(Date date) {
        final List<LessonPlan> lessonPlans = new ArrayList<>();
        AndroidNetworking.get(URLConstant.getInstance().getUrlGetListLessonPlanByDate(date.toFormatRequest()))
                .addHeaders("Authorization", "Bearer " + UserProfile.getInstance().accessToken)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getBoolean("success")) {
                                JSONArray lessonPlanJSONs = response.getJSONArray("lesson_plan");
                                for (int i = 0; i < lessonPlanJSONs.length(); i++) {
                                    lessonPlans.add(new LessonPlan(lessonPlanJSONs.getJSONObject(i)));
                                }
                            }
                            calendarFragment.setUpLessonPlanRecyclerView(lessonPlans);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }


}
