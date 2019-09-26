package com.example.swimminggo.view.coach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.swimminggo.R;
import com.example.swimminggo.adapter.ListAvailableSwimmerAdapter;
import com.example.swimminggo.adapter.ListSwimmerAdapter;
import com.example.swimminggo.models.Swimmer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AddSwimmer extends AppCompatActivity {

    FloatingActionButton fab,fab_new,fab_available;
    boolean showhide = false;
    ListSwimmerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_swimmer);
        fab = (FloatingActionButton) findViewById(R.id.btn_addswimmer);
        fab_new = (FloatingActionButton) findViewById(R.id.btn_addnew);
        fab_available = (FloatingActionButton)findViewById(R.id.btn_addavailable);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(showhide==false){
                    Show();
                    showhide = true;
                }else{
                    Hide();
                    showhide = false;
                }
            }
        });
        fab_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddSwimmer.this, AddNewSwimmer.class);
                startActivity(intent);
            }
        });
        fab_available.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddSwimmer.this, AddAvailableSwimmer.class);
                startActivity(intent);
            }
        });
    }

    private void Show(){
        fab_new.show();
        fab_available.show();
    }
    private  void Hide(){
        fab_new.hide();
        fab_available.hide();
    }
    private void setListTeamAdapter() {
        List<Swimmer> swimmers = new ArrayList<>();
        swimmers.add(new Swimmer());
        mAdapter = new ListSwimmerAdapter(swimmers);
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(AddSwimmer.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);
    }

}
