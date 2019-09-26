package com.example.swimminggo.constant;

public class URLConstant {
    public String mainUrl = "http://ec2-13-229-83-241.ap-southeast-1.compute.amazonaws.com";
    public String URL_LOGIN = mainUrl + "/api/public/login";
    public String URL_REGISTER = mainUrl +"/api/public/register";
    public String URL_GET_OTP = mainUrl + "/api/public/otp/send/";
    public String URL_FORGOT_PASSWORD = mainUrl + "/api/password/forgot";
    public String URL_CHANGE_PASSWORD = mainUrl + "/api/password/change";
    private static URLConstant ourInstance;

    public static URLConstant getInstance() {
        if (ourInstance == null)
            ourInstance = new URLConstant();
        return ourInstance;
    }

    public String getMainUrl(){
        return mainUrl;
    }

    public String getEditProfileUrl(int userId){
        return mainUrl + "/api/account/edit/" + userId;
    }

    public String getUrlCheckOtp(String otp){
        return mainUrl + "/api/otp/check/" + otp;
    }

    public String getOTPUrl(String email){
        return URL_GET_OTP + email;
    }

    private URLConstant() {
    }
}
