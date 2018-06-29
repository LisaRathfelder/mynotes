package com.lisarathfelder.mynotes.server;

import com.lisarathfelder.mynotes.shared.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginMapper {
	
private int maxId;

private static LoginMapper loginMapper = null;

/**
 * Geschützter Konstruktor - verhindert die Möglichkeit, mit new neue
 * Instanzen dieser Klasse zu erzeugen.
 * 
 */


protected LoginMapper() {
}

/**Diese Methode ist statisch. Sie stellt die 
	 * Singleton-Eigenschaft sicher, es kann nur eine Instanz von 
	 * NoteMapper  existieren.
	 * 
	 * @return LoginMapper-Objekt
	 */


public static LoginMapper loginMapper() {

	    if (loginMapper == null) {
	    	loginMapper = new LoginMapper();
	    }

	    return loginMapper;
}

	
	public User createUser(User user) throws IllegalArgumentException {
		//do a database connection and save the note object in database 
		Connection con = DBConnection.connection(); // 1. DB connection wird hergestellt
		try {
			
			Statement stmt=con.createStatement(); // 2. stmt Objekt wird generiert
			
			ResultSet rs=stmt.executeQuery("SELECT  MAX(userId) AS userId from users"); //3. Über stmt Objekt wird SQL-Befehl durchgeführt - auslesen
			
			if(rs.next()) {
				maxId=rs.getInt("userId");
				maxId++;
			}else {
				maxId=1;
			}
			
			
			 stmt.executeUpdate("INSERT INTO users(userId,userName,userPassword) VALUES ("
			 		+ maxId //3. Über stmt Objekt wird SQL-Befehl durchgeführt - in die DB schicken
			 		+ ",\"" +user.getUserName()+ "\""
			 		+ ",\"" +user.getuserPassword()+ "\""
			 		+ ")");
			                   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	public User getUserByUserName(String userName) throws IllegalArgumentException {

		User currentUser = new User();
		currentUser.setUserName(userName);
		Connection con = DBConnection.connection(); // 1. DB connection wird hergestellt
		try {
		Statement stmt=con.createStatement(); // 2. stmt Objekt wird generiert
		ResultSet rs=stmt.executeQuery("SELECT userId,userPassword from users where userName=\""
				+ userName
				+"\""); //3. Über stmt Objekt wird SQL-Befehl durchgeführt - auslesen
	    if(rs.next()) {
	    	currentUser.setUserID(Integer.parseInt(rs.getString("userId"))); 
	    	currentUser.setUserPassword(rs.getString("userPassword"));
	   	 }else {
	   		currentUser.setUserID(0); //user not existing 
	   	 }
		
		
	} catch (SQLException e) {  //Fehlerbehandlung
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return currentUser;		
		
		
		
	}
}
