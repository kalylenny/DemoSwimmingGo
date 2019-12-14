package com.example.swimminggo.view.coach.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swimminggo.R;
import com.example.swimminggo.adapter.ListTeamAdapter;
import com.example.swimminggo.constant.AgeConstant;
import com.example.swimminggo.models.Team;
import com.example.swimminggo.presenter.TeamPresenter;
import com.example.swimminggo.presenter.presenterImpl.TeamPresenterImpl;
import com.example.swimminggo.singleton.ListTeam;
import com.example.swimminggo.view.coach.SwipeController;
import com.example.swimminggo.view.coach.SwipeControllerActions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TeamFragment extends Fragment {

    public View view;
    private ListTeamAdapter mAdapter;
    private TeamPresenter teamPresenter;
    SwipeController swipeController = null;
    private Spinner spnListAge;
    public Dialog dialogAdd, dialogEdit;
    FloatingActionButton btn_addteam;
    List<Integer> listAge = AgeConstant.getInstance().listAge;

    public TeamFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_team, container, false);
        teamPresenter = new TeamPresenterImpl(this);
        initDatabase();
        setupRecyclerView();
        btn_addteam = (FloatingActionButton) view.findViewById(R.id.btn_add);
        btn_addteam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAdd = new Dialog(getContext());
                dialogAdd.setContentView(R.layout.dialog_add_team);
                spnListAge = dialogAdd.findViewById(R.id.spn_age);
                spnListAge.setAdapter(new ArrayAdapter<Integer>(getContext(), R.layout.support_simple_spinner_dropdown_item, listAge));
                final EditText edtTeamName = dialogAdd.findViewById(R.id.edt_team_name);
                Button btnAddTeam = dialogAdd.findViewById(R.id.btn_add_team);
                btnAddTeam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        teamPresenter.onAddTeam(new Team(edtTeamName.getText().toString(),Integer.parseInt(spnListAge.getSelectedItem().toString())));
                    }
                });

                Button btnCancel = dialogAdd.findViewById(R.id.btn_cancel);
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogAdd.dismiss();
                    }
                });

               dialogAdd.show();
            }
        });

        return view;
    }

    private void initDatabase() {
        if (ListTeam.getInstance() == null) {
            ListTeam.newInstance();
            teamPresenter.getListTeam();
            setupRecyclerView();
        } else {
            setupRecyclerView();
        }
    }

    public void setupRecyclerView() {
        mAdapter = new ListTeamAdapter(ListTeam.getInstance().getListTeam());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);

        swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
                showAlertDialog(position);
            }

            @Override
            public void onLeftClicked(int position) {
                loadEditTeamDialog(position, ListTeam.getInstance().getListTeam().get(position));
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

    private void showAlertDialog(final int position){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        teamPresenter.deleteTeam(position);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        dialog.dismiss();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Bạn có muốn xóa nhóm này?").setPositiveButton("Đồng ý", dialogClickListener)
                .setNegativeButton("Hủy", dialogClickListener).show();
    }

    private void loadEditTeamDialog(final int position, final Team team){
        dialogEdit = new Dialog(this.getContext());
        dialogEdit.setContentView(R.layout.dialog_edit_team);
        final EditText edtTeamName = dialogEdit.findViewById(R.id.edt_team_name);
        edtTeamName.setText(team.getTeamName());

        final Spinner spnAge = dialogEdit.findViewById(R.id.spn_age);
        spnAge.setAdapter(new ArrayAdapter<Integer>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, listAge));
        spnAge.setSelection(getPositionListAge(team.getTeamAge()));

        Button btnEdit = dialogEdit.findViewById(R.id.btn_edit_team);
        Button btnCancel = dialogEdit.findViewById(R.id.btn_cancel);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                team.setTeamAge(Integer.parseInt(spnAge.getSelectedItem().toString()));
                team.setTeamName(edtTeamName.getText().toString());
                teamPresenter.updateTeam(position, team);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogEdit.dismiss();
            }
        });
        dialogEdit.show();
    }

    private int getPositionListAge(int age){
        return listAge.indexOf(age);
    }

    public void doEditTeam(boolean result, String message){
        if (result){
//            mAdapter = new ListTeamAdapter(ListTeam.getInstance().getListTeam());
            setupRecyclerView();
            dialogEdit.dismiss();
        } else {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    public void doAddTeam(boolean result, String message) {
        if (result) {
            setupRecyclerView();
            dialogAdd.dismiss();
        } else {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }
    public void doDeleteTeam(Boolean result, String message) {
        if (result) {
            setupRecyclerView();
        } else{
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }



}
