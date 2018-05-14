package com.lisarathfelder.mynotes.server;

import com.lisarathfelder.mynotes.client.LoginService;
import com.lisarathfelder.mynotes.shared.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService { 
	
	
	
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



	
	
	
	
}
