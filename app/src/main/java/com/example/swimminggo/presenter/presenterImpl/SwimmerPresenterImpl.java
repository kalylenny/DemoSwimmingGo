package com.example.swimminggo.presenter.presenterImpl;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.swimminggo.constant.URLConstant;
import com.example.swimminggo.models.Swimmer;
import com.example.swimminggo.presenter.SwimmerPresenter;
import com.example.swimminggo.singleton.ListSwimmer;
import com.example.swimminggo.singleton.UserProfile;
import com.example.swimminggo.view.coach.AddSwimmer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SwimmerPresenterImpl implements SwimmerPresenter {

    AddSwimmer swimmer;

    public SwimmerPresenterImpl(AddSwimmer swimmer){
        this.swimmer = swimmer;
        AndroidNetworking.initialize(swimmer.getApplicationContext());
    }

    @Override
    public void onGetListSwimmerByTeamId(int teamId) {
        AndroidNetworking.get(URLConstant.getInstance().getUrlGetListSwimmer(teamId))
                .addHeaders("Authorization", "Bearer " + UserProfile.getInstance().accessToken)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getBoolean("success")){
                                ListSwimmer.newInstance();
                                JSONArray swimmerJSONArray = response.getJSONArray("swimmers");
                                for(int i = 0; i < swimmerJSONArray.length(); i++){
                                    ListSwimmer.getInstance().swimmers.add(new Swimmer(swimmerJSONArray.getJSONObject(i)));
                                    ListSwimmer.getInstance().isCheckeds.add(false);
                                }
                                swimmer.setListTeamAdapter(ListSwimmer.getInstance().swimmers);
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
    public void onRemoveSwimmersFromTeam(int teamId, final List<Integer> positions) {
        JSONObject swimmerIds = swimmerJSONIds(positions);
        AndroidNetworking.delete(URLConstant.getInstance().getUrlRemoveSwimmerFromTeam(teamId))
                .addHeaders("Authorization", "Bearer " + UserProfile.getInstance().accessToken)
                .addJSONObjectBody(swimmerIds)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getBoolean("success")){
                                swimmer.doRemoveSwimmer(positions);
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

    private JSONObject swimmerJSONIds(List<Integer> positions){
        JSONArray jsonArray = new JSONArray();
        JSONObject result = new JSONObject();
        for(Integer position : positions){
            jsonArray.put(ListSwimmer.getInstance().swimmers.get(position).getId());
        }
        try {
            result.put("swimmer_ids", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

}
