package com.example.swimminggo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swimminggo.R;
import com.example.swimminggo.models.Swimmer;

import java.util.List;

public class ListSwimmerAdapter extends RecyclerView.Adapter<ListSwimmerAdapter.SwimmerViewHolder> {

    public List<Swimmer> swimmers;

    @NonNull
    @Override
    public SwimmerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simple_list_swimmer, parent, false);

        return new SwimmerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SwimmerViewHolder holder, int position) {
        Swimmer swimmer = swimmers.get(position);
        holder.swimmerName.setText(swimmer.getFullName());
        holder.swimmerAge.setText(swimmer.getAge()+"");
    }

    @Override
    public int getItemCount() {
        return swimmers.size();
    }

    public class SwimmerViewHolder extends RecyclerView.ViewHolder {
        private TextView swimmerName, swimmerAge;

        public SwimmerViewHolder(View view) {
            super(view);
            swimmerName = (TextView) view.findViewById(R.id.name);
            swimmerAge = (TextView) view.findViewById(R.id.age);
        }
    }
    public ListSwimmerAdapter(List<Swimmer> swimmers) {
        this.swimmers=swimmers;
    }
}
