package com.lisarathfelder.mynotes.server;

import com.lisarathfelder.mynotes.client.NoteService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@SuppressWarnings("serial")
public class NoteServiceImpl  extends RemoteServiceServlet implements NoteService { 
	
	
	
	public String createNote(String noteText) throws IllegalArgumentException {
		
		//do a database connection and save the note object in database 
		Connection con = DBConnection.connection();
		try {
			Statement stmt=con.createStatement();
			 stmt.executeUpdate("INSERT INTO notes(nID,content) VALUES (3,\"" +noteText+ "\")");
			                   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//send an answer back to client
		return "Your note with the following content: <br><br>" + noteText + "!<br><br>is created <br>";
	}


	public String deleteNote(String noteText) throws IllegalArgumentException {
		
		//do a database connection and delete the note object in database 
		
		//send an answer back to client
		return "Your note with the following content: <br><br>" + noteText + "!<br><br>is deleted <br>";
	}
	
	
	
	
}
