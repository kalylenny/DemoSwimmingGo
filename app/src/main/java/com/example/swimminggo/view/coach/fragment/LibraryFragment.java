package com.example.swimminggo.view.coach.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swimminggo.R;
import com.example.swimminggo.adapter.VideoAdapter;
import com.example.swimminggo.presenter.VideoPresenter;
import com.example.swimminggo.presenter.presenterImpl.VideoPresenterImpl;
import com.example.swimminggo.singleton.Videos;

public class LibraryFragment extends Fragment {
    View view;
    VideoPresenter videoPresenter;
    RecyclerView recyclerView;
    Button btnAdd;
    public LibraryFragment(){

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

    private void initComponent(){
        videoPresenter = new VideoPresenterImpl(this);
        btnAdd = view.findViewById(R.id.btn_add);
        recyclerView = view.findViewById(R.id.recyclerView);
    }

    private void initDatabase(){
        videoPresenter.onGetVideos();
    }

    public void setupRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new VideoAdapter(Videos.getInstance().getVideos()));
    }

    private void action(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
