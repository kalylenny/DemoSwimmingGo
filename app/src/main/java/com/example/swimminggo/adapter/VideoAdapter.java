package com.example.swimminggo.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swimminggo.R;
import com.example.swimminggo.models.Video;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    List<Video> videos;
    View view;

    public VideoAdapter(List<Video> videos){
        this.videos = videos;
    }

    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.ViewHolder holder, int position) {
        holder.txtVideoName.setText(videos.get(position).getName());
        Picasso.get().load("https://img.youtube.com/vi/"+videos.get(position).getLink()+"/hqdefault.jpg").into(holder.imgView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/watch?v="+videos.get(position).getLink())));
            }
        });
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtVideoName;
        ImageView imgView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtVideoName = itemView.findViewById(R.id.txt_video_name);
            imgView = itemView.findViewById(R.id.img_view_video);
        }
    }
}
