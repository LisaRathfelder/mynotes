package com.lisarathfelder.mynotes.server;

import com.lisarathfelder.mynotes.client.NoteService;
import com.lisarathfelder.mynotes.shared.Note;
import com.lisarathfelder.mynotes.shared.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class NoteServiceImpl  extends RemoteServiceServlet implements NoteService { 
	
	private NoteMapper nMapper = null; //nMapper Variable wird definiert 
	
	public void initNoteMapperObject() throws IllegalArgumentException {

	    this.nMapper = NoteMapper.noteMapper(); 
	  /*
	   * noteMapper() Methode prüft ob es schon ein NoteMapper Objekt gibt, 
	   * Wenn es kein NoteMapper Objekt gibt - wird mit dieser Methode ein neues Objekt generiert
	   * Ansonsten werden vorhandene Objekte 
	   * und in die Variable nMapper gespeichert 
	   */
	    
	}
	
	//createNote
	public String createNote(Note note) throws IllegalArgumentException {
		return this.nMapper.createNote(note);
		/*
		 * Über nMapper Objekt wird die createNote Methode in der Mapperklasse aufgerufen.
		 * Diese Methode nimmt einkommende Note Objekte als Eingangsparameter.
		 */
	}


	
	//getAllNotesUser
	@Override
	public ArrayList<Note> getAllNotesUser(User user) throws IllegalArgumentException {
		return this.nMapper.getAllNotesUser(user);
	}


	//deleteNoteOfId
	@Override
	public String deleteNoteOfId(int id) throws IllegalArgumentException {
		return this.nMapper.deleteNoteOfId(id);
	}

	//getNoteOfId
	@Override
	public Note getNoteOfID(int id) throws IllegalArgumentException {
		return this.nMapper.getNoteOfID(id);
	}



	@Override
	public String editNote(Note note) throws IllegalArgumentException {
		return this.nMapper.editNote(note);
		
	}
}


