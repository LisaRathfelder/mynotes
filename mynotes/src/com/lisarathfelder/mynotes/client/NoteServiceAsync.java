package com.lisarathfelder.mynotes.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.lisarathfelder.mynotes.shared.Note;
import com.lisarathfelder.mynotes.shared.User;

public interface NoteServiceAsync {

	void createNote(Note note, AsyncCallback<String> callback);
	void getAllNotesUser(User user, AsyncCallback<ArrayList<Note>> callback);
	void deleteNoteOfId(int id, AsyncCallback<String> callback);
	void getNoteOfID(int id, AsyncCallback<Note> callback);

}
