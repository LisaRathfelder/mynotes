package com.lisarathfelder.mynotes.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath; //GWT Klassen für RPC Kommunikation
import com.lisarathfelder.mynotes.shared.Note;
import com.lisarathfelder.mynotes.shared.User;

@RemoteServiceRelativePath("noteservice")
public interface NoteService extends RemoteService {
	String createNote(Note note) throws IllegalArgumentException;
	ArrayList<Note> getAllNotesUser(User user) throws IllegalArgumentException;
	String deleteNoteOfId(int id) throws IllegalArgumentException;
	String editNote(Note note) throws IllegalArgumentException;
	Note getNoteOfID(int id) throws IllegalArgumentException;
	void initNoteMapperObject() throws IllegalArgumentException;

}
