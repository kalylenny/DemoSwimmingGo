package com.example.swimminggo.presenter;

import com.example.swimminggo.models.Video;

public interface VideoPresenter {
    void onGetVideos();
    void onAddVideos(Video video);
    void onDeleteVideo();
}
