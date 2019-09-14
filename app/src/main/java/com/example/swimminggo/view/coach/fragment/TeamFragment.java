package com.example.swimminggo.view.coach.fragment;

import android.os.Bundle;
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
import com.example.swimminggo.view.coach.SwipeController;
import com.example.swimminggo.view.coach.SwipeControllerActions;

import java.util.ArrayList;
import java.util.List;

public class TeamFragment extends Fragment {

    View view;
    private ListTeamAdapter mAdapter;
    SwipeController swipeController = null;

    public TeamFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_team, container, false);
        setListTeamAdapter();
        setupRecyclerView();
        return view;
    }

    private void setListTeamAdapter() {
        List<Team> team = new ArrayList<>();
        team.add(new Team("Nhóm bơi nam 1","15"));
        team.add(new Team("Nhóm bơi nam 1","15"));
        team.add(new Team("Nhóm bơi nam 1","15"));
        team.add(new Team("Nhóm bơi nam 1","15"));
        team.add(new Team("Nhóm bơi nam 1","15"));
        team.add(new Team("Nhóm bơi nam 1","15"));
        team.add(new Team("Nhóm bơi nam 1","15"));
        team.add(new Team("Nhóm bơi nam 1","15"));
        team.add(new Team("Nhóm bơi nam 1","15"));
        team.add(new Team("Nhóm bơi nam 1","15"));
        team.add(new Team("Nhóm bơi nam 1","15"));
        team.add(new Team("Nhóm bơi nam 1","15"));
        team.add(new Team("Nhóm bơi nam 1","15"));
        team.add(new Team("Nhóm bơi nam 1","15"));
        team.add(new Team("Nhóm bơi nam 1","15"));
        team.add(new Team("Nhóm bơi nam 1","15"));
        team.add(new Team("Nhóm bơi nam 1","15"));
        team.add(new Team("Nhóm bơi nam 1","15"));
        team.add(new Team("Nhóm bơi nam 1","15"));
        team.add(new Team("Nhóm bơi nam 1","15"));
        team.add(new Team("Nhóm bơi nam 1","15"));
//            String line;
//            String[] st;
//            while ((line = reader.readLine()) != null) {
//                st = line.split(",");
//                Player player = new Player();
//                player.setName(st[0]);
//                player.setNationality(st[1]);
//                player.setClub(st[4]);
//                player.setRating(Integer.parseInt(st[9]));
//                player.setAge(Integer.parseInt(st[14]));
//                players.add(player);
//            }
//        } catch (IOException e) {
//
//        }

        mAdapter = new ListTeamAdapter(team);
    }

    private void setupRecyclerView() {
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
