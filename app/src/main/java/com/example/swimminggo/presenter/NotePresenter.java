package com.example.swimminggo.presenter;

import com.example.swimminggo.models.Note;

public interface NotePresenter {

    public void getNotesBySwimmerId(int swimmerId);
    public void onAddNote(Note note);
}
