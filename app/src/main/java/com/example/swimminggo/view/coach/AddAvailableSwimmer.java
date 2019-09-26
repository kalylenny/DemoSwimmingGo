package com.example.swimminggo.view.coach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.swimminggo.R;
import com.example.swimminggo.adapter.ListAvailableSwimmerAdapter;
import com.example.swimminggo.models.Swimmer;

import java.util.ArrayList;
import java.util.List;

public class AddAvailableSwimmer extends AppCompatActivity {

    private ListAvailableSwimmerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_available_swimmer);
        setupRecyclerView();
    }

    private void setListTeamAdapter() {
        List<Swimmer> swimmers = new ArrayList<>();
        swimmers.add(new Swimmer());
        mAdapter = new ListAvailableSwimmerAdapter(swimmers);
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(AddAvailableSwimmer.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);
    }
}
