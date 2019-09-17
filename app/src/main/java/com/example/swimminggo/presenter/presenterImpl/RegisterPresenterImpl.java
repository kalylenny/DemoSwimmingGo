package com.example.swimminggo.presenter.presenterImpl;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.swimminggo.constant.URLConstant;
import com.example.swimminggo.presenter.RegisterPresenter;
import com.example.swimminggo.view.Register;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterPresenterImpl implements RegisterPresenter {

    Register register;

    public RegisterPresenterImpl(Register register) {
        this.register = register;
        AndroidNetworking.initialize(register.getApplicationContext());
    }

    @Override
    public void onRegister(String username, String password, String email) {
        final JSONObject registerObject = registerObject(username, password, email);
        AndroidNetworking.post(URLConstant.getInstance().URL_REGISTER)
                .addJSONObjectBody(registerObject)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getBoolean("success")) {
                                register.doRegister(true, "Đăng ký thành công");
                            } else {
                                register.doRegister(false, response.getString("message"));
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

    @Override
    public void validateForm(String username, String password, String rePassword, String email) {
        if (!checkUsername(username)) {
            register.doRegister(false, "Tên đăng nhập không hợp lệ");
            return;
        } else if (!checkPassword(password)) {
            register.doRegister(false, "Mật khẩu không hợp lệ");
            return;
        } else if (!checkRepassword(password, rePassword)) {
            register.doRegister(false, "Mật khẩu không trùng");
            return;
        } else if (!checkEmail(email)) {
            register.doRegister(false, "Email không đúng");
            return;
        }
        onRegister(username, password, email);
    }

    private boolean checkUsername(String username){
        return !username.equals("");
    }

    private boolean checkPassword(String password){
        return !password.equals("");
    }

    private boolean checkRepassword(String password, String rePassword){
        return password.equals(rePassword);
    }

    private boolean checkEmail(String email){
        return email.contains("@");
    }

    public JSONObject registerObject(String username, String password, String email) {
        JSONObject result = new JSONObject();
        try {
            result.put("username", username);
            result.put("password", password);
            result.put("email", email);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }


}
