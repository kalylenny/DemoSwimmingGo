package com.example.swimminggo.presenter.presenterImpl;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.swimminggo.constant.URLConstant;
import com.example.swimminggo.presenter.ChangePasswordPresenter;
import com.example.swimminggo.singleton.UserProfile;
import com.example.swimminggo.view.profile.ChangePassword;

import org.json.JSONException;
import org.json.JSONObject;

public class ChangePasswordPresenterImpl implements ChangePasswordPresenter {

    private ChangePassword changePassword;

    public ChangePasswordPresenterImpl(ChangePassword changePassword) {
        this.changePassword = changePassword;
        AndroidNetworking.initialize(changePassword.getApplicationContext());
    }

    @Override
    public void onvalidatePassword(String password, String rePassword) {
        if (password.equals(rePassword)) {
            changePassword.doValidatePassword(true, "Mật khẩu hợp lệ");
        } else {
            changePassword.doValidatePassword(false, "Mật khẩu mới không trùng");
        }
    }

    @Override
    public void onSubmit(String oldPassword, String newPassword) {
        JSONObject password = passwordObject(oldPassword, newPassword);
        AndroidNetworking.put(URLConstant.getInstance().URL_CHANGE_PASSWORD)
                .addJSONObjectBody(password)
                .addHeaders("Authorization", "Bearer " + UserProfile.getInstance().accessToken)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getBoolean("success")){
                                changePassword.doSubmit(true, "Đổi mật khẩu thành công");
                            } else {
                                changePassword.doSubmit(true, response.getString("message"));
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

    private JSONObject passwordObject(String oldPassword, String newPassword) {
        JSONObject result = new JSONObject();
        try {
            result.put("old_password", oldPassword);
            result.put("new_password", newPassword);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
