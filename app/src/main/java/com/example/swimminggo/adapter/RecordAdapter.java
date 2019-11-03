package com.example.swimminggo.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swimminggo.R;
import com.example.swimminggo.models.Record;
import com.example.swimminggo.models.Swimmer;
import com.example.swimminggo.singleton.Records;

import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {
    List<Swimmer> swimmers;
    View view;

    public RecordAdapter(List<Swimmer> swimmers){
        this.swimmers = swimmers;
    }

    @NonNull
    @Override
    public RecordAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_record, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordAdapter.ViewHolder holder, int position) {
        holder.txtSwimName.setText(swimmers.get(position).getFullName());
        holder.edtMin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().equals(""))
                    Records.getInstance().getRecords().get(position).setMin(Integer.parseInt(editable.toString()));
            }
        });
        holder.edtSec.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().equals(""))
                    Records.getInstance().getRecords().get(position).setSec(Integer.parseInt(editable.toString()));
            }
        });
        holder.edtMillisec.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().equals(""))
                    Records.getInstance().getRecords().get(position).setMillisec(Integer.parseInt(editable.toString()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return swimmers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtSwimName;
        EditText edtMin, edtSec, edtMillisec;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSwimName = itemView.findViewById(R.id.txt_swim_name);
            edtMin = itemView.findViewById(R.id.edt_min);
            edtSec = itemView.findViewById(R.id.edt_sec);
            edtMillisec = itemView.findViewById(R.id.edt_millisec);
        }
    }
}
