package com.example.swimminggo.view.coach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.swimminggo.R;
import com.example.swimminggo.models.Account;
import com.example.swimminggo.presenter.SwimmerPresenter;
import com.example.swimminggo.presenter.presenterImpl.SwimmerPresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class AddNewSwimmer extends AppCompatActivity {

    ElegantNumberButton numberButton;
    ListView listViewSwimmer;
    Button btnFinish, btnCreateAccount;
    SwimmerPresenter swimmerPresenter;
    List<Account> swimmerAccounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_swimmer);
        initComponent();
        action();
    }

    private void initComponent() {
        swimmerPresenter = new SwimmerPresenterImpl(this);
        listViewSwimmer = findViewById(R.id.list_swimmer);
        numberButton = (ElegantNumberButton) findViewById(R.id.number_button);

        numberButton.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                String num = numberButton.getNumber();
            }
        });
        numberButton.setRange(1, 10);

        btnFinish = findViewById(R.id.btn_finish);
        btnCreateAccount = findViewById(R.id.btn_create_account);
    }

    private void action() {
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swimmerPresenter.onGetListNewSwimmer(Integer.parseInt(numberButton.getNumber()));
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swimmerPresenter.onAddNewSwimmer(swimmerAccounts);
            }
        });
    }

    public void setupListView(List<Account> accounts) {
        swimmerAccounts = accounts;
        List<String> dataAccounts = new ArrayList<>();
        for (Account account : accounts) {
            dataAccounts.add("Username : " + account.getUsername() + ", password : " + account.getPassword());
        }
        listViewSwimmer.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataAccounts));
    }

    public void doAddSwimmer(Boolean result, String message) {
        if (result) {
            startActivity(new Intent(AddNewSwimmer.this, AddNewSwimmer.class));
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

}
