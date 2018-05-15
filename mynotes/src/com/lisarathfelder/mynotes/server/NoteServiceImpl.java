package com.lisarathfelder.mynotes.server;

import com.lisarathfelder.mynotes.client.NoteService;
import com.lisarathfelder.mynotes.shared.Note;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

@SuppressWarnings("serial")
public class NoteServiceImpl  extends RemoteServiceServlet implements NoteService { 
	
	
	
	public String createNote(Note note) throws IllegalArgumentException {
		
		//do a database connection and save the note object in database 
		Connection con = DBConnection.connection();
		try {
			
			SimpleDateFormat mySQLformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			Statement stmt=con.createStatement();
			 stmt.executeUpdate("INSERT INTO notes(nID,content,title,creDate,modDate,userName) VALUES (2"
			 		+ ",\"" +note.getContent()+ "\""
			 		+ ",\"" +note.getTitle()+ "\""
			 		+ ",\"" +mySQLformat.format(note.getCreatDate())+ "\""
					+ ",\"" +mySQLformat.format(note.getModDate())+ "\""
					+ ",\"" +note.getUserName()+ "\""
			 		+ ")");
			                   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//send an answer back to client
		return "Your note with the following content: <br><br>" + note.getContent() + "!<br><br>is created <br>";
	}


	public String deleteNote(Note note) throws IllegalArgumentException {
		
		//do a database connection and delete the note object in database 
		
		//send an answer back to client
		return "Your note with the following content: <br><br>" + note.getContent() + "!<br><br>is deleted <br>";
	}
	
	
	
	
}
