package com.example.swimminggo.presenter.presenterImpl;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.swimminggo.constant.URLConstant;
import com.example.swimminggo.models.Video;
import com.example.swimminggo.presenter.VideoPresenter;
import com.example.swimminggo.singleton.UserProfile;
import com.example.swimminggo.singleton.Videos;
import com.example.swimminggo.view.coach.fragment.Library;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VideoPresenterImpl implements VideoPresenter {

    Library library;

    public VideoPresenterImpl(Library library) {
        this.library = library;
        AndroidNetworking.initialize(library.getApplicationContext());
    }

    @Override
    public void onGetVideos() {
        AndroidNetworking.get(URLConstant.getInstance().URL_GET_VIDEO)
                .addHeaders("Authorization", "Bearer " + UserProfile.getInstance().accessToken)
                .build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                Videos.newInstance();
                try {
                    if (response.getBoolean("success")) {
                        JSONArray jsonArray = response.getJSONArray("videos");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Videos.getInstance().getVideos().add(new Video(jsonArray.getJSONObject(i)));
                        }
                        library.setupRecyclerView();
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
    public void onAddVideos(Video video) {
        AndroidNetworking.post(URLConstant.getInstance().URL_ADD_VIDEO)
                .addJSONObjectBody(video.toJSONObject())
                .addHeaders("Authorization", "Bearer " + UserProfile.getInstance().accessToken)
                .build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                library.doAddVideo(response);
            }

            @Override
            public void onError(ANError anError) {

            }
        });
    }

    @Override
    public void onDeleteVideo() {

    }
}
