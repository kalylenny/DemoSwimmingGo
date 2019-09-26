package com.example.swimminggo.view.coach.fragment;

import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.swimminggo.R;
import com.example.swimminggo.adapter.ListTeamAdapter;
import com.example.swimminggo.models.Team;
import com.example.swimminggo.presenter.TeamPresenter;
import com.example.swimminggo.presenter.presenterImpl.TeamPresenterImpl;
import com.example.swimminggo.singleton.ListTeam;
import com.example.swimminggo.view.coach.SwipeController;
import com.example.swimminggo.view.coach.SwipeControllerActions;

import java.util.ArrayList;
import java.util.List;

public class TeamFragment extends Fragment {

    public View view;
    private ListTeamAdapter mAdapter;
    private TeamPresenter teamPresenter;
    SwipeController swipeController = null;

    public TeamFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_team, container, false);
        teamPresenter = new TeamPresenterImpl(this);
        initDatabase();
        return view;
    }

    private void initDatabase(){
        if (ListTeam.getInstance() == null){
            ListTeam.newInstance();
            teamPresenter.getListTeam();
        } else {
            setupRecyclerView();
        }
    }

    public void setupRecyclerView() {
        mAdapter = new ListTeamAdapter(ListTeam.getInstance().getListTeam());
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);

        swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
                mAdapter.teams.remove(position);
                mAdapter.notifyItemRemoved(position);
                mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount());
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

}
