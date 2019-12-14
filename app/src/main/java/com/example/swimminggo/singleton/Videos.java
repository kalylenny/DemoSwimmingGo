package com.example.swimminggo.singleton;

import com.example.swimminggo.models.Video;

import java.util.ArrayList;
import java.util.List;

public class Videos {
    private static Videos ourInstance;
    List<Video> videos;

    public static Videos getInstance() {
        return ourInstance;
    }

    public static void newInstance(){
        ourInstance = new Videos();
    }

    private Videos() {
        videos = new ArrayList<>();
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void clearData() {
        ourInstance = null;
    }
}
