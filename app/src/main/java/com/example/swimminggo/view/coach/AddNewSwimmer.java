package com.example.swimminggo.view.coach;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.example.swimminggo.models.Team;
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
    Team team;
    AddSwimmer addSwimmer;
    public ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_swimmer);
        initComponent();
        action();
    }

    private void initComponent() {
        team = (Team) getIntent().getSerializableExtra("team");
        swimmerPresenter = new SwimmerPresenterImpl(this);
        listViewSwimmer = findViewById(R.id.lv_swimmer);
        numberButton = (ElegantNumberButton) findViewById(R.id.number_button);

        numberButton.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                String num = numberButton.getNumber();
            }
        });
        numberButton.setRange(1, 10);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Vui lòng chờ");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMax(100);
        progressDialog.incrementProgressBy(2);
        btnFinish = findViewById(R.id.btn_finish);
        btnCreateAccount = findViewById(R.id.btn_create_workout);
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
                progressDialog.show();
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
            this.finish();
            this.addSwimmer = (AddSwimmer) AddSwimmer.addSwimmerActivity;
            addSwimmer.initDatabase();
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

}
