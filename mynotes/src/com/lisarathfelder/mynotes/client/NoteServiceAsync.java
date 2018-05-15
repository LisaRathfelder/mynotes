package com.lisarathfelder.mynotes.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.lisarathfelder.mynotes.shared.Note;

public interface NoteServiceAsync {

	void createNote(Note note, AsyncCallback<String> callback);
	void deleteNote(Note note, AsyncCallback<String> callback);

}
