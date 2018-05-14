package com.lisarathfelder.mynotes.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath; //GWT Klassen für RPC Kommunikation

@RemoteServiceRelativePath("noteservice")
public interface NoteService extends RemoteService {
	String createNote(String note) throws IllegalArgumentException;
	String deleteNote(String note) throws IllegalArgumentException;
}
