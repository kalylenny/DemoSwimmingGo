package com.example.swimminggo.presenter.presenterImpl;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.swimminggo.constant.URLConstant;
import com.example.swimminggo.presenter.ForgotPasswordPresenter2;
import com.example.swimminggo.view.ForgotPassword2;

import org.json.JSONObject;

public class ForgotPasswordPresenterImpl2 implements ForgotPasswordPresenter2 {

    ForgotPassword2 forgotPassword2;

    public ForgotPasswordPresenterImpl2(ForgotPassword2 forgotPassword2){
        this.forgotPassword2 = forgotPassword2;
        AndroidNetworking.initialize(forgotPassword2.getApplicationContext());
    }
    @Override
    public void onSendOtp(String otp) {
        AndroidNetworking.get(URLConstant.getInstance().getUrlCheckOtp(otp))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            forgotPassword2.doSendOtp(response.getBoolean("success"), response.getString("otp"));
                        }catch(Exception e){

                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }
}
