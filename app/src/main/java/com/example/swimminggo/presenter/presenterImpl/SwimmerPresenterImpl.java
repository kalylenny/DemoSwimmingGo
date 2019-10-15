package com.example.swimminggo.presenter.presenterImpl;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.swimminggo.constant.URLConstant;
import com.example.swimminggo.models.Account;
import com.example.swimminggo.models.Swimmer;
import com.example.swimminggo.models.Team;
import com.example.swimminggo.presenter.SwimmerPresenter;
import com.example.swimminggo.singleton.UserProfile;
import com.example.swimminggo.view.coach.AddNewSwimmer;
import com.example.swimminggo.view.coach.AddSwimmer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SwimmerPresenterImpl implements SwimmerPresenter {

    private AddSwimmer swimmer;
    private AddNewSwimmer addNewSwimmer;
    private Team currentTeam;

    public SwimmerPresenterImpl(AddSwimmer swimmer) {
        this.swimmer = swimmer;
        AndroidNetworking.initialize(swimmer.getApplicationContext());
    }

    public SwimmerPresenterImpl(AddNewSwimmer addNewSwimmer) {
        this.addNewSwimmer = addNewSwimmer;
        this.currentTeam = (Team) addNewSwimmer.getIntent().getSerializableExtra("team");
        AndroidNetworking.initialize(addNewSwimmer.getApplicationContext());
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
                            if (response.getBoolean("success")) {
                                List<Swimmer> swimmers = new ArrayList<>();
                                JSONArray swimmerJSONArray = response.getJSONArray("swimmers");
                                for (int i = 0; i < swimmerJSONArray.length(); i++) {
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

    @Override
    public void onGetListNewSwimmer(int number) {
        final List<Account> accounts = new ArrayList<>();
        AndroidNetworking.get(URLConstant.getInstance().getUrlAddNewSwimmer(number))
                .addHeaders("Authorization", "Bearer " + UserProfile.getInstance().accessToken)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getBoolean("success")) {
                                JSONArray accountsJSON = response.getJSONArray("swimmers");
                                for (int i = 0; i < accountsJSON.length(); i++) {
                                    accounts.add(new Account(accountsJSON.getJSONObject(i)));
                                }
                                addNewSwimmer.setupListView(accounts);
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
    public void onAddNewSwimmer(List<Account> accounts) {
        JSONObject accountObjects = addAccountObjects(accounts);
        AndroidNetworking.post(URLConstant.getInstance().getUrlAddSwimmer(currentTeam.getTeamID()))
                .addJSONObjectBody(accountObjects)
                .addHeaders("Authorization", "Bearer " + UserProfile.getInstance().accessToken)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getBoolean("success")){
                                addNewSwimmer.doAddSwimmer(true, "Success");
                            } else {
                                addNewSwimmer.doAddSwimmer(false, "False");
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

    private JSONObject addAccountObjects(List<Account> accounts) {
        JSONObject result = new JSONObject();
        JSONArray swimmers = new JSONArray();
        try {
            for (Account account : accounts) {
                JSONObject accountObject = new JSONObject();
                accountObject.put("username", account.getUsername());
                accountObject.put("password", account.getPassword());
                swimmers.put(accountObject);
            }
            result.put("swimmers", swimmers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
