package com.lisarathfelder.mynotes.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface NoteMapperAsync {

	void createNote(String note, AsyncCallback<String> callback) throws IllegalArgumentException;
	void deleteNote(String note, AsyncCallback<String> callback) throws IllegalArgumentException;

}
