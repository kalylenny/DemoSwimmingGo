package com.example.swimminggo.view.coach.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swimminggo.R;
import com.example.swimminggo.adapter.VideoAdapter;
import com.example.swimminggo.models.Video;
import com.example.swimminggo.presenter.VideoPresenter;
import com.example.swimminggo.presenter.presenterImpl.VideoPresenterImpl;
import com.example.swimminggo.singleton.UserProfile;
import com.example.swimminggo.singleton.Videos;

public class LibraryFragment extends Fragment {
    View view;
    VideoPresenter videoPresenter;
    RecyclerView recyclerView;
    Button btnAdd;
    Dialog dialog;

    public LibraryFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_library, container, false);
        initComponent();
        initDatabase();
        action();
        return view;
    }

    private void initComponent() {
        videoPresenter = new VideoPresenterImpl(this);
        btnAdd = view.findViewById(R.id.btn_add);
        if (UserProfile.getInstance().currentUser.getRoleName().equals("swimmer"))
            btnAdd.setVisibility(View.GONE);
        recyclerView = view.findViewById(R.id.recyclerView);
    }

    private void initDatabase() {
        videoPresenter.onGetVideos();
    }

    public void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new VideoAdapter(Videos.getInstance().getVideos()));
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
        dialog = new Dialog(this.getContext());
        dialog.setContentView(R.layout.dialog_add_video);

        EditText edtVideoName = dialog.findViewById(R.id.edt_video_name);
        EditText edtLink = dialog.findViewById(R.id.edt_link);
        Button btnAddVideo = dialog.findViewById(R.id.btn_add_video);
        Button btnCancel = dialog.findViewById(R.id.btn_cancel);

        btnAddVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoPresenter.onAddVideos(new Video(edtVideoName.getText().toString(), edtLink.getText().toString()));
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
            dialog.dismiss();
        } else {
            Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

}
