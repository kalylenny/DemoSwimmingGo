package com.example.swimminggo.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swimminggo.R;
import com.example.swimminggo.models.Style;
import com.example.swimminggo.view.coach.fragment.Library;
import com.example.swimminggo.view.coach.fragment.LibraryCategories;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    List<Style> styles;
    View itemView;
    LibraryCategories libraryCategories;

    public CategoryAdapter(List<Style> styles, LibraryCategories libraryCategories){
        this.styles = styles;
        this.libraryCategories = libraryCategories;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        holder.imgViewVideo.setBackgroundResource(R.drawable.ic_ytb);
        holder.txtVideoName.setText(styles.get(position).getValue());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), Library.class);
                intent.putExtra("style", styles.get(position));
                itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return styles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgViewVideo;
        TextView txtVideoName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgViewVideo = itemView.findViewById(R.id.img_video);
            txtVideoName = itemView.findViewById(R.id.txt_category_name);
        }
    }
}
