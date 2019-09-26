package com.example.swimminggo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swimminggo.R;
import com.example.swimminggo.models.Team;

import java.util.List;

public class ListTeamAdapter extends RecyclerView.Adapter<ListTeamAdapter.TeamViewHolder> {
    public List<Team> teams;

    public class TeamViewHolder extends RecyclerView.ViewHolder {
        private TextView teamName, teamAge;

        public TeamViewHolder(View view) {
            super(view);
            teamName = (TextView) view.findViewById(R.id.name);
            teamAge = (TextView) view.findViewById(R.id.age);
        }
    }

    public ListTeamAdapter(List<Team> teams) {
        this.teams=teams;
    }

    @Override
    public TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simple_team_row, parent, false);

        return new TeamViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TeamViewHolder holder, int position) {
        Team team = teams.get(position);
        holder.teamName.setText(team.getTeamName());
        holder.teamAge.setText(team.getTeamAge()+"");
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }
}

