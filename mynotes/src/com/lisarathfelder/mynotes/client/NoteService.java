package com.lisarathfelder.mynotes.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath; //GWT Klassen für RPC Kommunikation
import com.lisarathfelder.mynotes.shared.Note;

@RemoteServiceRelativePath("noteservice")
public interface NoteService extends RemoteService {
	String createNote(Note note) throws IllegalArgumentException;
	String deleteNote(Note note) throws IllegalArgumentException;
}
