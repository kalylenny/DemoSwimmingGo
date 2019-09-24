package com.example.swimminggo.presenter.presenterImpl;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.swimminggo.constant.URLConstant;
import com.example.swimminggo.presenter.ForgotPasswordPresenter;
import com.example.swimminggo.view.ForgotPassword1;

import org.json.JSONException;
import org.json.JSONObject;

public class ForgotPasswordPresenterImpl implements ForgotPasswordPresenter {

    ForgotPassword1 forgotPassword1;

    public ForgotPasswordPresenterImpl(ForgotPassword1 forgotPassword1) {
        this.forgotPassword1 = forgotPassword1;
        AndroidNetworking.initialize(forgotPassword1.getApplicationContext());
    }

    @Override
    public void onValidateMail(String email) {
        forgotPassword1.doValidateMail(checkMail(email));
    }

    private boolean checkMail(String email) {
        return email.contains("@");
    }

    @Override
    public void onSendMail(String email) {
        AndroidNetworking.get(URLConstant.getInstance().getOTPUrl(email))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            forgotPassword1.doSendMail(response.getBoolean("success"));
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
