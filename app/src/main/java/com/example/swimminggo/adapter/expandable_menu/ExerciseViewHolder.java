package com.example.swimminggo.adapter.expandable_menu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.swimminggo.R;
import com.example.swimminggo.constant.ExerciseConstant;
import com.example.swimminggo.models.Distance;
import com.example.swimminggo.models.Exercise;
import com.example.swimminggo.models.Phase;
import com.example.swimminggo.models.Style;
import com.example.swimminggo.view.coach.ExerciseActivity;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public class ExerciseViewHolder extends ChildViewHolder {

    private TextView txtDistance, txtStyle, txtRep;
    private TextView txtOption;

    public ExerciseViewHolder(View itemView) {
        super(itemView);
        txtDistance = itemView.findViewById(R.id.txt_distance);
        txtStyle = itemView.findViewById(R.id.txt_style);
        txtRep = itemView.findViewById(R.id.txt_rep);
        txtOption = itemView.findViewById(R.id.txt_option);
    }

    public void onBind(Exercise exercise, ExerciseActivity exerciseActivity) {
        txtDistance.setText("Khoảng cách : " + exercise.getDistance());
        txtRep.setText("Số lần tập : " + exercise.getRep());
        String style = Iterables.tryFind(ExerciseConstant.getInstance().getStyles(), new Predicate<Style>() {
            @Override
            public boolean apply(@NullableDecl Style input) {
                return input.getId().equals(exercise.getStyleId());
            }
        }).or(new Style()).getValue();
        txtStyle.setText("Kiểu bơi : " + style);
        txtOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu pop = new PopupMenu(itemView.getContext(), txtOption);
                pop.inflate(R.menu.menu_option);
                pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.item_edit){
                            Dialog dialog = new Dialog(itemView.getContext());
                            dialog.setContentView(R.layout.dialog_edit_exercise);
                            Spinner spnPhase, spnDistance, spnRep, spnStyle;
                            Button btnEdit, btnCancel;

                            btnEdit = dialog.findViewById(R.id.btn_edit);
                            btnCancel = dialog.findViewById(R.id.btn_cancel);

                            spnPhase = dialog.findViewById(R.id.spn_phase);
                            spnDistance = dialog.findViewById(R.id.spn_distance);
                            spnRep = dialog.findViewById(R.id.spn_rep);
                            spnStyle = dialog.findViewById(R.id.spn_style);

                            spnPhase.setAdapter(new ArrayAdapter<>(itemView.getContext(), android.R.layout.simple_spinner_dropdown_item, ExerciseConstant.getInstance().getPhases()));
                            spnRep.setAdapter(new ArrayAdapter<>(itemView.getContext(), android.R.layout.simple_spinner_dropdown_item, ExerciseConstant.getInstance().getReps()));
                            spnDistance.setAdapter(new ArrayAdapter<>(itemView.getContext(), android.R.layout.simple_spinner_dropdown_item, ExerciseConstant.getInstance().getDistances()));
                            spnStyle.setAdapter(new ArrayAdapter<>(itemView.getContext(), android.R.layout.simple_spinner_dropdown_item, ExerciseConstant.getInstance().getStyles()));

                            spnPhase.setSelection(ExerciseConstant.getInstance().getPhases().indexOf(new Phase(exercise.getPhaseId())));
                            spnRep.setSelection(ExerciseConstant.getInstance().getReps().indexOf(exercise.getRep()));
                            spnDistance.setSelection(ExerciseConstant.getInstance().getDistances().indexOf(new Distance(exercise.getDistance())));
                            spnStyle.setSelection(ExerciseConstant.getInstance().getStyles().indexOf(new Style(exercise.getStyleId())));

                            int exerciseId = exercise.getId();

                            btnEdit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Style style = (Style) spnStyle.getSelectedItem();
                                    Distance distance = (Distance) spnDistance.getSelectedItem();
                                    Phase phase = (Phase) spnPhase.getSelectedItem();
                                    dialog.dismiss();
                                    exerciseActivity.onEditExercise(new Exercise(exerciseId, style.getId(), distance.getValue(),(int) spnRep.getSelectedItem(), phase.getId()));
                                }
                            });

                            btnCancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });

                            dialog.show();
                        } else {
                            showAlertDialog(exercise.getId());
                        }
                        pop.dismiss();
                        return false;
                    }
                    private void showAlertDialog(final int exerciseId){
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case DialogInterface.BUTTON_POSITIVE:
                                        exerciseActivity.onDeleteExercise(exerciseId);
                                        break;

                                    case DialogInterface.BUTTON_NEGATIVE:
                                        dialog.dismiss();
                                        break;
                                }
                            }
                        };

                        AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                        builder.setMessage("Bạn muốn xóa bài tập này?").setPositiveButton("Đồng ý", dialogClickListener)
                                .setNegativeButton("Hủy", dialogClickListener).show();
                    }
                });
                pop.show();
            }
        });
    }


}
