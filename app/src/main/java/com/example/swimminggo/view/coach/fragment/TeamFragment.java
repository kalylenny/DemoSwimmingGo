package com.example.swimminggo.view.coach.fragment;

import android.app.Dialog;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.swimminggo.R;
import com.example.swimminggo.adapter.ListTeamAdapter;
import com.example.swimminggo.constant.AgeConstant;
import com.example.swimminggo.models.Team;
import com.example.swimminggo.presenter.TeamPresenter;
import com.example.swimminggo.presenter.presenterImpl.TeamPresenterImpl;
import com.example.swimminggo.singleton.ListTeam;
import com.example.swimminggo.view.coach.SwipeController;
import com.example.swimminggo.view.coach.SwipeControllerActions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TeamFragment extends Fragment {

    public View view;
    private ListTeamAdapter mAdapter;
    private TeamPresenter teamPresenter;
    SwipeController swipeController = null;
    private Spinner spnListAge;
    public Dialog dialog;
    FloatingActionButton btn_addteam;

    public TeamFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_team, container, false);
        teamPresenter = new TeamPresenterImpl(this);
        initDatabase();
        setupRecyclerView();
        btn_addteam = (FloatingActionButton) view.findViewById(R.id.btn_add);
        btn_addteam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_add_team);
                spnListAge = dialog.findViewById(R.id.spn_age);
                spnListAge.setAdapter(new ArrayAdapter<Integer>(getContext(), R.layout.support_simple_spinner_dropdown_item, AgeConstant.getInstance().listAge));
                final EditText edtTeamName = dialog.findViewById(R.id.edt_team_name);
                Button btnAddTeam = dialog.findViewById(R.id.btn_add_team);
                btnAddTeam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        teamPresenter.onAddTeam(new Team(edtTeamName.getText().toString(),Integer.parseInt(spnListAge.getSelectedItem().toString())));
                    }
                });

                Button btnCancel = dialog.findViewById(R.id.btn_cancel);
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

               dialog.show();
            }
        });

        return view;
    }

    private void initDatabase() {
        if (ListTeam.getInstance() == null) {
            ListTeam.newInstance();
            teamPresenter.getListTeam();
        } else {
            setupRecyclerView();
        }
    }

    public void setupRecyclerView() {
        mAdapter = new ListTeamAdapter(ListTeam.getInstance().getListTeam());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);

        swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
                teamPresenter.deleteTeam(position);
            }

            @Override
            public void onLeftClicked(int position) {

            }
        });

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });
    }

    public void doAddTeam(boolean result) {
        if (result) {
            mAdapter = new ListTeamAdapter(ListTeam.getInstance().getListTeam());
            dialog.dismiss();
        }
    }
    public void doDeleteTeam(Boolean result, String message) {
        if (result) {
            mAdapter.notifyDataSetChanged();
        } else{
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }



}
