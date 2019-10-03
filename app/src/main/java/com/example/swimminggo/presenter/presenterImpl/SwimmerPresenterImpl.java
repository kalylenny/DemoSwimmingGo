package com.example.swimminggo.presenter.presenterImpl;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.swimminggo.constant.URLConstant;
import com.example.swimminggo.models.Swimmer;
import com.example.swimminggo.presenter.SwimmerPresenter;
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
                                List<Swimmer> swimmers = new ArrayList<>();
                                JSONArray swimmerJSONArray = response.getJSONArray("swimmers");
                                for(int i = 0; i < swimmerJSONArray.length(); i++){
                                    swimmers.add(new Swimmer(swimmerJSONArray.getJSONObject(i)));
                                }
                                swimmer.setListTeamAdapter(swimmers);
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
}
