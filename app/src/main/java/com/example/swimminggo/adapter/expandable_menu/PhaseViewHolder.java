package com.example.swimminggo.adapter.expandable_menu;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.swimminggo.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

public class PhaseViewHolder extends GroupViewHolder {

    private TextView phaseTitle;
    private ImageView imgViewPhase;

    public PhaseViewHolder(View itemView) {
        super(itemView);
        phaseTitle = itemView.findViewById(R.id.txt_phase_title);
        imgViewPhase = itemView.findViewById(R.id.img_view_phase);
    }

    public void setPhaseTitle(ExpandableGroup group, String phaseName) {
        phaseTitle.setText(group.getTitle());
        if (phaseName.equals("Warm Up"))
            imgViewPhase.setBackgroundResource(R.drawable.warmup64);
        else if (phaseName.equals("Main Stroke"))
            imgViewPhase.setBackgroundResource(R.drawable.main64);
        else if (phaseName.equals("Final Set"))
            imgViewPhase.setBackgroundResource(R.drawable.final64);
        else
            imgViewPhase.setBackgroundResource(R.drawable.relax64);
    }
}
