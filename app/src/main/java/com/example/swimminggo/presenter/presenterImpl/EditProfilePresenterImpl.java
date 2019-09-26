package com.example.swimminggo.presenter.presenterImpl;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.swimminggo.constant.URLConstant;
import com.example.swimminggo.models.User;
import com.example.swimminggo.presenter.EditProfilePresenter;
import com.example.swimminggo.singleton.UserProfile;
import com.example.swimminggo.view.profile.EditProfile;

import org.json.JSONException;
import org.json.JSONObject;

public class EditProfilePresenterImpl implements EditProfilePresenter {

    EditProfile editProfile;
    User currentUser = UserProfile.getInstance().currentUser;

    public EditProfilePresenterImpl(EditProfile editProfile) {
        this.editProfile = editProfile;
        AndroidNetworking.initialize(editProfile.getApplicationContext());
    }


    public String checkForm(User dataUser) {
        if (dataUser.getFirstName().equals("") || dataUser.getLastName().equals("")) {
            return "Tên không hợp lệ";
        }
        if (!(dataUser.getPhone().matches("-?\\d+(\\.\\d+)?") && dataUser.getPhone().length() >= 10)) {
            return "Số điện thoại không hợp lệ";
        }
        if (!dataUser.getEmail().contains("@")) {
            return "Email không hợp lệ";
        }
        return "success";
    }

    @Override
    public void onValidateForm(User dataUser) {
        Log.d("dataUser", dataUser.toString());
        String message = checkForm(dataUser);
        boolean result = message.equals("success");
        editProfile.doValidateForm(result, message);
    }

    @Override
    public void onEdit(User dataUser) {
        JSONObject profileObject = convertProfileToJSONObject(dataUser);

        AndroidNetworking.put(URLConstant.getInstance().getEditProfileUrl(currentUser.getId()))
                .addJSONObjectBody(profileObject)
                .addHeaders("Authorization", "Bearer " + UserProfile.getInstance().accessToken)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("JSON", response.toString());
                            if (response.getBoolean("success")) {
                                editProfile.doEditProfile(true, "Sửa thành công");
                            } else {
                                editProfile.doEditProfile(false, response.getString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        editProfile.doEditProfile(false, "Something was error");
                    }
                });
    }

    private JSONObject convertProfileToJSONObject(User dataUser) {
        JSONObject result = new JSONObject();
        try {
            result.put("user", dataUser.convertToJSONObject());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
