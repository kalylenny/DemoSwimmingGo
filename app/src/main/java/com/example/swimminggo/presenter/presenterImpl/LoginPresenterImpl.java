package com.example.swimminggo.presenter.presenterImpl;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.swimminggo.constant.URLConstant;
import com.example.swimminggo.models.User;
import com.example.swimminggo.singleton.UserProfile;
import com.example.swimminggo.view.Login;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginPresenterImpl implements com.example.swimminggo.presenter.LoginPresenter {
    Login loginView;
    Handler handler;

    public LoginPresenterImpl(Login loginView) {
        this.loginView = loginView;
        handler = new Handler(Looper.getMainLooper());
        AndroidNetworking.initialize(loginView.getApplicationContext());
    }

    @Override
    public void onLogin(final String username, String password) {
        if (!isValidateUserName(username)) {
            Toast.makeText(loginView, "Tên đăng nhập sai", Toast.LENGTH_SHORT).show();
        } else if (!isValidatePassowrd(password)){
            Toast.makeText(loginView, "Mật khẩu sai", Toast.LENGTH_SHORT).show();
        }else {
            AndroidNetworking.post(URLConstant.getInstance().URL_LOGIN)
                    .addJSONObjectBody(loginObject(username, password))
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            loginView.progressDialog.dismiss();
                            try {
                                if (response.getBoolean("success")) {
                                    UserProfile.getInstance().getUserProfileByJSONObject(response.getJSONObject("user"));
                                    UserProfile.getInstance().setAccessToken(response.getString("token"));
                                    loginView.onLoginResult(true, "Đăng nhập thành công");
                                } else {
                                    loginView.onLoginResult(false, response.getString("message"));
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
    }

    private boolean isValidateUserName(String username) {
        return !username.equals("");
    }

    private boolean isValidatePassowrd(String password){
        return !password.equals("");
    }

    private JSONObject loginObject(String username, String password) {
        JSONObject result = new JSONObject();
        try {
            result.put("username", username);
            result.put("password", password);
        } catch (Exception e) {

        }
        return result;
    }

}
