package com.lisarathfelder.mynotes.server;

import com.lisarathfelder.mynotes.client.NoteService;
import com.lisarathfelder.mynotes.shared.Note;
import com.lisarathfelder.mynotes.shared.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class NoteServiceImpl  extends RemoteServiceServlet implements NoteService { 
	
	private int maxNId;
	
	public String createNote(Note note) throws IllegalArgumentException {
		 
		//do a database connection and save the note object in database 
		Connection con = DBConnection.connection(); // 1. DB connection wird hergestellt
		try {
			
			
			SimpleDateFormat mySQLformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			Statement stmt=con.createStatement(); // 2. stmt Objekt wird generiert
			
			ResultSet rs=stmt.executeQuery("SELECT  MAX(nId) AS nId from notes"); //3. Über stmt Objekt wird SQL-Befehl durchgeführt - auslesen
			
			if(rs.next()) {
				maxNId=rs.getInt("nId");
				maxNId++;
			}
			
			
			 stmt.executeUpdate("INSERT INTO notes(nId,content,title,creDate,modDate,userName) VALUES ("
			 		+ maxNId //3. Über stmt Objekt wird SQL-Befehl durchgeführt - in die DB schicken
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



	@Override
	public ArrayList<Note> getAllNotesUser(User user) throws IllegalArgumentException {
		
		ArrayList<Note> AllUserNotes = new ArrayList<Note>();
		
		Connection con = DBConnection.connection(); // 1. DB connection wird hergestellt
		
		try {
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT nId,title from notes where userName=\""
					+ user.getUserName()
					+ "\""); //3. Über stmt Objekt wird SQL-Befehl durchgeführt - auslesen
			while(rs.next()) {
				Note note=new Note();
				note.setNoteID(rs.getInt("nId"));
				note.setTitle(rs.getString("title"));
				AllUserNotes.add(note);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 2. stmt Objekt wird generiert
		return AllUserNotes;
	}



	@Override
	public String deleteNoteOfId(int id) throws IllegalArgumentException {
		Connection con = DBConnection.connection(); // 1. DB connection wird hergestellt
		
		try {
			Statement stmt=con.createStatement();
			stmt.executeUpdate("delete from notes where nId="
					+ id);
			
		} catch (SQLException e) {  //Fehlerbehandlung
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}



	@Override
	public Note getNoteOfID(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}




	
	
	
}
