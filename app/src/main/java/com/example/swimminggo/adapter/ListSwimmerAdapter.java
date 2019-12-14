package com.example.swimminggo.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swimminggo.R;
import com.example.swimminggo.models.Swimmer;
import com.example.swimminggo.singleton.ListSwimmer;
import com.example.swimminggo.view.SwimmerProfile;

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
    public void onBindViewHolder(@NonNull SwimmerViewHolder holder, final int position) {
        Swimmer swimmer = swimmers.get(position);
        holder.swimmerName.setText(swimmer.getFullName());
        holder.swimmerAge.setText(swimmer.getAge()==0?"Chưa cập nhật" : swimmer.getAge()+"");
        holder.checkBox.setChecked(ListSwimmer.getInstance().isCheckeds.get(position));
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ListSwimmer.getInstance().isCheckeds.set(position, isChecked);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), SwimmerProfile.class);
                intent.putExtra("swimmer", swimmer);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return swimmers.size();
    }

    public class SwimmerViewHolder extends RecyclerView.ViewHolder {
        private TextView swimmerName, swimmerAge;
        private CheckBox checkBox;

        public SwimmerViewHolder(View view) {
            super(view);
            swimmerName = (TextView) view.findViewById(R.id.name);
            swimmerAge = (TextView) view.findViewById(R.id.age);
            checkBox = view.findViewById(R.id.check_box);
        }
    }

    public ListSwimmerAdapter(List<Swimmer> swimmers) {
        this.swimmers = swimmers;
    }
}
