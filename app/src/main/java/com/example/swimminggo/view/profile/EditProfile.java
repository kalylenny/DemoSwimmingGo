package com.example.swimminggo.view.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swimminggo.R;
import com.example.swimminggo.models.Coach;
import com.example.swimminggo.models.Swimmer;
import com.example.swimminggo.models.User;
import com.example.swimminggo.presenter.EditProfilePresenter;
import com.example.swimminggo.presenter.presenterImpl.EditProfilePresenterImpl;
import com.example.swimminggo.singleton.UserProfile;
import com.example.swimminggo.view.coach.MainActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class EditProfile extends AppCompatActivity {

    User currentUser = UserProfile.getInstance().currentUser;
    User dataUser;
    EditProfilePresenter editProfilePresenter;
    ImageView imgAvatar;
    EditText edtFirstName, edtLastName, edtEmail, edtPhone;
    TextView txtDob;
    Spinner spnGender;
    Button btnSave;
    public static Activity editProfile;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        initComponentView();
        initDatabase();
        action();
    }

    private void initComponentView() {
        editProfile = this;

        editProfilePresenter = new EditProfilePresenterImpl(this);

        //imgAvatar = findViewById(R.id.img_avatar);

        edtFirstName = findViewById(R.id.edt_first_name);
        edtLastName = findViewById(R.id.edt_last_name);
        edtEmail = findViewById(R.id.edt_email);
        edtPhone = findViewById(R.id.edt_phone);
        txtDob = findViewById(R.id.txt_dob);

        spnGender = findViewById(R.id.spn_gender);

        btnSave = findViewById(R.id.btn_save);
        initDatePickerDialog();
    }

    private void initDatePickerDialog(){
        final Calendar calendar = Calendar.getInstance();
        int mYear = calendar.get(calendar.YEAR);
        int mMonth = calendar.get(calendar.MONTH);
        int mDay = calendar.get(calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                txtDob.setText(year + "/" + (month + 1) + "/" +dayOfMonth);
                    }
                }, mYear, mMonth, mDay);
    }

    private void initDatabase() {
        ArrayAdapter<String> listSexAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, new ArrayList<String>(Arrays.asList("Nam", "Nữ")));
        spnGender.setAdapter(listSexAdapter);
        edtEmail.setText(currentUser.getEmail().equals("null")?"":currentUser.getEmail());
        txtDob.setText(currentUser.getDob().equals("null")?"":currentUser.getDob());
        edtFirstName.setText(currentUser.getFirstName().equals("null")?"":currentUser.getFirstName());
        edtLastName.setText(currentUser.getLastName().equals("null")?"":currentUser.getLastName());
        edtPhone.setText(currentUser.getPhone());
    }

    public void action() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataUser = addDataUser();
                editProfilePresenter.onValidateForm(dataUser);
            }
        });
        txtDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
    }

    private User addDataUser() {
        User user;
        if (currentUser.getRoleName().equals("coach")) {
            user = new Coach();
        } else {
            user = new Swimmer();
        }
        user.setFirstName(edtFirstName.getText().toString());
        user.setEmail(edtEmail.getText().toString());
        user.setDob(txtDob.getText().toString());
        user.setLastName(edtLastName.getText().toString());
        user.setGender(spnGender.getSelectedItem().toString().equals("Nam")? 1:0);
        user.setPhone(edtPhone.getText().toString());
        return user;
    }

    public void doValidateForm(boolean result, String message) {
        if (result) {
            editProfilePresenter.onEdit(dataUser);
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    public void doEditProfile(boolean result, String message) {
        addDataUser();
        if (result) {
            changeDataUser();
            finish();
            if (ViewProfile.viewProfile != null)
                ViewProfile.viewProfile.finish();
            else if (currentUser.getRoleName().equals("coach")) {
                startActivity(new Intent(EditProfile.this, MainActivity.class));
            } else {
                startActivity(new Intent(EditProfile.this, com.example.swimminggo.view.swimmer.MainActivity.class));
            }
        } else{
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    private void changeDataUser(){
        UserProfile.getInstance().currentUser.setFirstName(edtFirstName.getText().toString());
        UserProfile.getInstance().currentUser.setEmail(edtEmail.getText().toString());
        UserProfile.getInstance().currentUser.setDob(txtDob.getText().toString());
        UserProfile.getInstance().currentUser.setLastName(edtLastName.getText().toString());
        UserProfile.getInstance().currentUser.setGender(spnGender.getSelectedItem().toString().equals("Nam")? 1:0);
        UserProfile.getInstance().currentUser.setPhone(edtPhone.getText().toString());
    }
}
