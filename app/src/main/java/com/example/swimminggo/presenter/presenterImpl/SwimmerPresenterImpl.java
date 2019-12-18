package com.example.swimminggo.presenter.presenterImpl;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.swimminggo.constant.URLConstant;
import com.example.swimminggo.models.Account;
import com.example.swimminggo.models.Swimmer;
import com.example.swimminggo.models.Team;
import com.example.swimminggo.presenter.SwimmerPresenter;
import com.example.swimminggo.singleton.ListSwimmer;
import com.example.swimminggo.singleton.UserProfile;
import com.example.swimminggo.view.coach.AddAvailableSwimmer;
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
    private AddAvailableSwimmer addAvailableSwimmer;
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

    public SwimmerPresenterImpl(AddAvailableSwimmer addAvailableSwimmer) {
        this.addAvailableSwimmer = addAvailableSwimmer;
        this.currentTeam = (Team) addAvailableSwimmer.getIntent().getSerializableExtra("team");
        AndroidNetworking.initialize(addAvailableSwimmer.getApplicationContext());
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
                                ListSwimmer.newInstance();
                                JSONArray swimmerJSONArray = response.getJSONArray("swimmers");
                                for (int i = 0; i < swimmerJSONArray.length(); i++) {
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
        AndroidNetworking.put(URLConstant.getInstance().getUrlRemoveSwimmerFromTeam(teamId))
                .addHeaders("Authorization", "Bearer " + UserProfile.getInstance().accessToken)
                .addJSONObjectBody(swimmerIds)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getBoolean("success")) {
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

    private JSONObject swimmerJSONIds(List<Integer> positions) {
        JSONArray jsonArray = new JSONArray();
        JSONObject result = new JSONObject();
        try {
            for (Integer position : positions) {
                JSONObject id = new JSONObject();
                id.put("id", ListSwimmer.getInstance().swimmers.get(position).getId());
                jsonArray.put(id);
            }
            result.put("swimmers", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
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
    public void onGetListSwimmerNoTeam() {
        final List<Swimmer> swimmers = new ArrayList<>();
        AndroidNetworking.get(URLConstant.getInstance().URL_GET_SWIMMER_NOTEAM)
                .addHeaders("Authorization", "Bearer " + UserProfile.getInstance().accessToken)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getBoolean("success")) {
                                JSONArray swimmerJSONs = response.getJSONArray("swimmers");
                                for (int i = 0; i < swimmerJSONs.length(); i++) {
                                    swimmers.add(new Swimmer(swimmerJSONs.getJSONObject(i)));
                                }
                                addAvailableSwimmer.setListTeamAdapter(swimmers);
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
                        addNewSwimmer.progressDialog.dismiss();
                        try {
                            if (response.getBoolean("success")) {
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

    @Override
    public void onAddSwimmerToTeam(List<Swimmer> swimmers) {
        JSONObject swimmerJSONs = addSwimmerObjects(swimmers);
        AndroidNetworking.post(URLConstant.getInstance().getUrlAddSwimmerNoTeam(currentTeam.getTeamID()))
                .addJSONObjectBody(swimmerJSONs)
                .addHeaders("Authorization", "Bearer " + UserProfile.getInstance().accessToken)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getBoolean("success")){
                                addAvailableSwimmer.doAddSwimmerToTeam(true, "Success");
                            } else {
                                addAvailableSwimmer.doAddSwimmerToTeam(false, "False");
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

    private JSONObject addSwimmerObjects(List<Swimmer> swimmers) {
        JSONObject result = new JSONObject();
        JSONArray swimmerJSONs = new JSONArray();
        try {
            for(Swimmer swimmer : swimmers){
                JSONObject id = new JSONObject();
                id.put("id", swimmer.getId());
                swimmerJSONs.put(id);
            }
            result.put("swimmers", swimmerJSONs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
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
