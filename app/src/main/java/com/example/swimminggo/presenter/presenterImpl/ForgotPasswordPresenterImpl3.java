package com.example.swimminggo.presenter.presenterImpl;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.swimminggo.constant.URLConstant;
import com.example.swimminggo.presenter.ForgotPasswordPresenter3;
import com.example.swimminggo.view.ForgotPassword3;

import org.json.JSONException;
import org.json.JSONObject;

public class ForgotPasswordPresenterImpl3 implements ForgotPasswordPresenter3 {

    ForgotPassword3 forgotPassword3;

    public ForgotPasswordPresenterImpl3(ForgotPassword3 forgotPassword3) {
        this.forgotPassword3 = forgotPassword3;
        AndroidNetworking.initialize(forgotPassword3.getApplicationContext());
    }

    @Override
    public void onValidatePassword(String password, String rePassword) {
        if (!password.equals(rePassword)) {
            forgotPassword3.doValidatePassword(false, "Mật khẩu không hợp lệ");
        } else {
            forgotPassword3.doValidatePassword(true, "Mật khẩu hợp lệ");
        }
    }

    @Override
    public void onSubmit(String password, String otp) {
        JSONObject result = addForgotPasswordObject(password, otp);
        AndroidNetworking.put(URLConstant.getInstance().URL_FORGOT_PASSWORD)
                .addJSONObjectBody(result)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            forgotPassword3.doSubmit(response.getBoolean("success"), response.getString("message"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    private JSONObject addForgotPasswordObject(String password, String otp) {
        JSONObject result = new JSONObject();
        try {
            result.put("password", password);
            result.put("otp", otp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
