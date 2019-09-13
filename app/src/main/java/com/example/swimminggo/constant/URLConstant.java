package com.example.swimminggo.constant;

public class URLConstant {
    public String mainUrl = "https://198.168.1.178:5000";
    public String URL_LOGIN = mainUrl + "/api/public/login";

    private static URLConstant ourInstance;

    public static URLConstant getInstance() {
        if (ourInstance == null)
            ourInstance = new URLConstant();
        return ourInstance;
    }

    public String getMainUrl(){
        return mainUrl;
    }

    private URLConstant() {
    }
}
