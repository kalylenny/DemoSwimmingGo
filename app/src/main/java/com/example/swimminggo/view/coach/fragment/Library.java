package com.example.swimminggo.view.coach.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.swimminggo.R;
import com.example.swimminggo.adapter.VideoAdapter;
import com.example.swimminggo.models.Style;
import com.example.swimminggo.models.Video;
import com.example.swimminggo.presenter.VideoPresenter;
import com.example.swimminggo.presenter.presenterImpl.VideoPresenterImpl;
import com.example.swimminggo.singleton.UserProfile;
import com.example.swimminggo.singleton.Videos;

import java.util.ArrayList;
import java.util.List;

public class Library extends AppCompatActivity {

    VideoPresenter videoPresenter;
    RecyclerView recyclerView;
    Button btnAdd;
    Dialog dialog;
    Style style;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        initComponent();
        initDatabase();
        action();
    }

    private void initComponent() {
        style = (Style) getIntent().getSerializableExtra("style");
        videoPresenter = new VideoPresenterImpl(this);
        btnAdd = findViewById(R.id.btn_add);
        if (UserProfile.getInstance().currentUser.getRoleName().equals("swimmer"))
            btnAdd.setVisibility(View.GONE);
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void initDatabase() {
        videoPresenter.onGetVideos();
    }

    public void setupRecyclerView() {
        List<Video> videosByStyle = new ArrayList<>();
        for (Video video : Videos.getInstance().getVideos())
            if (video.getStyleId().equals(style.getId()))
                videosByStyle.add(video);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new VideoAdapter(videosByStyle));
    }

    private void action() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    private void showDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_video);

        EditText edtVideoName = dialog.findViewById(R.id.edt_video_name);
        EditText edtLink = dialog.findViewById(R.id.edt_link);
        Button btnAddVideo = dialog.findViewById(R.id.btn_add_video);
        Button btnCancel = dialog.findViewById(R.id.btn_cancel);

        btnAddVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoPresenter.onAddVideos(new Video(edtVideoName.getText().toString(), edtLink.getText().toString(), style.getId()));
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void doAddVideo(Boolean result, String message) {
        if (result) {
            initDatabase();
            dialog.dismiss();
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
}
