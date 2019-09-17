package com.example.swimminggo.constant;

public class URLConstant {
    public String mainUrl = "http://ec2-13-229-83-241.ap-southeast-1.compute.amazonaws.com";
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
