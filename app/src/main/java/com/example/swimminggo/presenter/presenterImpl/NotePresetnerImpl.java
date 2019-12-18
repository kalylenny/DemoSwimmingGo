package com.example.swimminggo.presenter.presenterImpl;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.swimminggo.constant.URLConstant;
import com.example.swimminggo.models.Note;
import com.example.swimminggo.presenter.NotePresenter;
import com.example.swimminggo.singleton.UserProfile;
import com.example.swimminggo.view.Notification;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NotePresetnerImpl implements NotePresenter {

    Notification notification;

    public NotePresetnerImpl(Notification notification) {
        this.notification = notification;
        AndroidNetworking.initialize(notification.getApplicationContext());
    }

    @Override
    public void getNotesBySwimmerId(int swimmerId) {
        AndroidNetworking.get(URLConstant.getInstance().getUrlGetNotesBySwimmerId(swimmerId))
                .addHeaders("Authorization", "Bearer " + UserProfile.getInstance().accessToken)
                .build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray noteArray = response.getJSONArray("note");
                    List<Note> notes = new ArrayList<>();
                    for (int i = 0; i < noteArray.length(); i++) {
                        notes.add(new Note(noteArray.getJSONObject(i)));
                    }
                    notification.setUpRecyclerViewNotification(notes);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ANError anError) {

            }
        });
    }

    @Override
    public void onAddNote(Note note) {
        AndroidNetworking.post(URLConstant.getInstance().URL_ADD_NOTE)
                .addJSONObjectBody(note.toJSONObject())
                .addHeaders("Authorization", "Bearer " + UserProfile.getInstance().accessToken)
                .build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                try {
//                    Boolean result = response.getBoolean("success");
//                    String message = response.getString("message");
                    if (response.getBoolean("success"))
                        notification.doAddNote(true, "ASDASD");
                    else
                        notification.doAddNote(false, response.getString("message"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ANError anError) {

            }
        });
    }
}
