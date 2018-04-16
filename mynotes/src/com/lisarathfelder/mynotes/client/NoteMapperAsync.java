package com.lisarathfelder.mynotes.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface NoteMapperAsync {

	void greetServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;

}
