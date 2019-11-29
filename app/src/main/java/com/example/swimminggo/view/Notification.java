package com.example.swimminggo.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swimminggo.R;
import com.example.swimminggo.adapter.NoteAdapter;
import com.example.swimminggo.models.Note;
import com.example.swimminggo.presenter.NotePresenter;
import com.example.swimminggo.presenter.presenterImpl.NotePresetnerImpl;
import com.example.swimminggo.singleton.UserProfile;
import com.whiteelephant.monthpicker.MonthPickerDialog;

import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

public class Notification extends AppCompatActivity {

    ImageView imageViewAddNotification;
    RecyclerView recyclerViewNotification;
    NoteAdapter noteAdapter;
    NotePresenter notePresenter;
    int swimmerId;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initComponent();
        initDatabase();
        action();
    }

    private void initComponent() {
        swimmerId = getIntent().getIntExtra("swimmer_id", 0);
        notePresenter = new NotePresetnerImpl(this);
        imageViewAddNotification = findViewById(R.id.img_add_notification);
        if (UserProfile.getInstance().currentUser.getRoleName().equals("swimmer")) {
            imageViewAddNotification.setVisibility(View.GONE);
        }
        recyclerViewNotification = findViewById(R.id.recycler_view_note);
    }

    private void action() {
        imageViewAddNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddNoteDialog();
            }
        });
    }

    private void showAddNoteDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_note);
        LinearLayout layoutDate = dialog.findViewById(R.id.layout_date);
        EditText edtNote = dialog.findViewById(R.id.edt_note);
        TextView txtDate = dialog.findViewById(R.id.txt_date);
        Button btnAdd = dialog.findViewById(R.id.btn_add_note);
        Button btnCancel = dialog.findViewById(R.id.btn_cancel);
        layoutDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(Notification.this, new MonthPickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(int selectedMonth, int selectedYear) {
                        txtDate.setText((selectedMonth + 1) + "/" + selectedYear);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
                builder.setTitle("Select month")
                        .setMonthAndYearRange(Calendar.JANUARY, Calendar.DECEMBER, 1900, 2030)
                        .build().show();

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringTokenizer tokens = new StringTokenizer(txtDate.getText().toString(), "/");
                int month = Integer.parseInt(tokens.nextToken());
                int year = Integer.parseInt(tokens.nextToken());
                Note note = new Note(swimmerId, month, year, edtNote.getText().toString());
                notePresenter.onAddNote(note);
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

    private void initDatabase() {
        onGetNotesBySwimmerId(swimmerId);
    }

    public void onGetNotesBySwimmerId(int swimmerId) {
        notePresenter.getNotesBySwimmerId(swimmerId);
    }

    public void setUpRecyclerViewNotification(List<Note> notes) {
        noteAdapter = new NoteAdapter(notes);
        recyclerViewNotification.setAdapter(noteAdapter);
        recyclerViewNotification.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    public void doAddNote(Boolean result, String message) {
        if (result) {
            initDatabase();
            dialog.dismiss();
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
}
