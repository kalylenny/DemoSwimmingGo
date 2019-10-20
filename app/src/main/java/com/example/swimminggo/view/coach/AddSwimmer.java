package com.example.swimminggo.view.coach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swimminggo.R;
import com.example.swimminggo.adapter.ListSwimmerAdapter;
import com.example.swimminggo.models.Swimmer;
import com.example.swimminggo.models.Team;
import com.example.swimminggo.presenter.SwimmerPresenter;
import com.example.swimminggo.presenter.presenterImpl.SwimmerPresenterImpl;
import com.example.swimminggo.singleton.ListSwimmer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AddSwimmer extends AppCompatActivity {

    FloatingActionButton fab, fab_new, fab_available;
    Team currentTeam;
    TextView txtTeamName, txtTeamAge;
    ImageView imgRemoveSwimmer;
    boolean showhide = false;
    ListSwimmerAdapter mAdapter;

    SwimmerPresenter swimmerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_swimmer);
        initComponent();
        initDatabase();
        action();
    }

    private void initComponent() {
        swimmerPresenter = new SwimmerPresenterImpl(this);

        txtTeamName = findViewById(R.id.txt_team_name);
        txtTeamAge = findViewById(R.id.txt_age);

        imgRemoveSwimmer = findViewById(R.id.img_delete);

        fab = (FloatingActionButton) findViewById(R.id.btn_addswimmer);
        fab_new = (FloatingActionButton) findViewById(R.id.btn_addnew);
        fab_available = (FloatingActionButton) findViewById(R.id.btn_addavailable);
    }

    private void initDatabase() {
        currentTeam = (Team) getIntent().getSerializableExtra("team");
        txtTeamName.setText(currentTeam.getTeamName());
        txtTeamAge.setText("Độ tuổi :" + currentTeam.getTeamAge());
        swimmerPresenter.onGetListSwimmerByTeamId(currentTeam.getTeamID());
    }

    private void action() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (showhide == false) {
                    Show();
                    showhide = true;
                } else {
                    Hide();
                    showhide = false;
                }
            }
        });
        fab_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddSwimmer.this, AddNewSwimmer.class);
                intent.putExtra("team", currentTeam);
                startActivity(intent);
            }
        });
        fab_available.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddSwimmer.this, AddAvailableSwimmer.class);
                intent.putExtra("team", currentTeam);
                startActivity(intent);
            }
        });

        imgRemoveSwimmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Integer> positions = new ArrayList<>();
                for (int i = 0; i < ListSwimmer.getInstance().isCheckeds.size(); i++)
                    if (ListSwimmer.getInstance().isCheckeds.get(i)) {
                        positions.add(i);
                    }
                swimmerPresenter.onRemoveSwimmersFromTeam(currentTeam.getTeamID(), positions);
            }
        });
    }

    private void Show() {
        fab_new.show();
        fab_available.show();
    }

    private void Hide() {
        fab_new.hide();
        fab_available.hide();
    }

    public void setListTeamAdapter(List<Swimmer> swimmers) {
        mAdapter = new ListSwimmerAdapter(swimmers);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list_swimmer);

        recyclerView.setLayoutManager(new LinearLayoutManager(AddSwimmer.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);
    }

    public void doRemoveSwimmer(List<Integer> positions) {
        swimmerPresenter.onGetListSwimmerByTeamId(currentTeam.getTeamID());
    }

}
