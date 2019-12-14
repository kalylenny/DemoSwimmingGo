package com.example.swimminggo.view.coach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swimminggo.R;
import com.example.swimminggo.adapter.ListAvailableSwimmerAdapter;
import com.example.swimminggo.models.Swimmer;
import com.example.swimminggo.models.Team;
import com.example.swimminggo.presenter.SwimmerPresenter;
import com.example.swimminggo.presenter.presenterImpl.SwimmerPresenterImpl;
import com.example.swimminggo.singleton.ListCheckBoxSwimmer;

import java.util.ArrayList;
import java.util.List;

public class AddAvailableSwimmer extends AppCompatActivity {

    private ListAvailableSwimmerAdapter mAdapter;
    private Team team;
    private SwimmerPresenter swimmerPresenter;
    private RecyclerView recyclerView;
    private Button btnAddSwimmer;
    private List<Swimmer> swimmers = new ArrayList<>();
    AddSwimmer addSwimmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_available_swimmer);
        initComponent();
        action();
    }

    private void initComponent() {
        team = (Team) getIntent().getSerializableExtra("team");
        swimmerPresenter = new SwimmerPresenterImpl(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        btnAddSwimmer = findViewById(R.id.btn_add_swimmer);

        swimmerPresenter.onGetListSwimmerNoTeam();
    }

    private void action() {
        btnAddSwimmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Swimmer> checkSwimmers = new ArrayList<>();
                List<Boolean> checkBoxs = ListCheckBoxSwimmer.getInstance().checkSwimmers;
                for (int i = 0; i < checkBoxs.size(); i++)
                    if (checkBoxs.get(i)) {
                        checkSwimmers.add(swimmers.get(i));
                    }
                swimmerPresenter.onAddSwimmerToTeam(checkSwimmers);
            }
        });
    }

    public void setListTeamAdapter(List<Swimmer> swimmers) {
        ListCheckBoxSwimmer.newInstance();
        this.swimmers = swimmers;
        for (Swimmer swimmer : swimmers) {
            ListCheckBoxSwimmer.getInstance().checkSwimmers.add(false);
        }
        mAdapter = new ListAvailableSwimmerAdapter(swimmers);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(AddAvailableSwimmer.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);
    }

    public void doAddSwimmerToTeam(Boolean result, String message) {
        if (result) {
            this.finish();
            this.addSwimmer = (AddSwimmer) AddSwimmer.addSwimmerActivity;
            addSwimmer.initDatabase();
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
}
