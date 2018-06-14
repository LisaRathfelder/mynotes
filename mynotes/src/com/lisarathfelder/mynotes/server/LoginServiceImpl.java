package com.lisarathfelder.mynotes.server;

import com.lisarathfelder.mynotes.client.LoginService;
import com.lisarathfelder.mynotes.shared.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService { 
	
	private int maxId;
	
	public String login(User user) throws IllegalArgumentException {
		
		//do a database connection and check user information
		
		//send an answer back to client
		return "User: " + user.getUserName() + " is logged in <br>"; //
	}
	
	public String logout(User user) throws IllegalArgumentException {
		
		//do a database connection and check user information
		
		//send an answer back to client
		return "User: " + user.getUserName() + " is logged out <br>"; //
	}

	@Override
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
		return null;
	}

	@Override
	public User getUserByUserName(String userName) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}



	
	
	
	
}
