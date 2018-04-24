package com.lisarathfelder.mynotes.server;

import com.lisarathfelder.mynotes.client.NoteMapper;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class NoteMapperImpl  extends RemoteServiceServlet implements NoteMapper { 
	
	
	
	public String createNote(String noteText) throws IllegalArgumentException {
		
		//do a database connection and save the note object in database 
		
		//send an answer back to client
		return "Your note with the following content: <br><br>" + noteText + "!<br><br>is created <br>";
	}


	public String deleteNote(String noteText) throws IllegalArgumentException {
		
		//do a database connection and delete the note object in database 
		
		//send an answer back to client
		return "Your note with the following content: <br><br>" + noteText + "!<br><br>is deleted <br>";
	}
	
	
	
	
}
