package com.lisarathfelder.mynotes.server;

import com.lisarathfelder.mynotes.client.LoginMapper;
import com.lisarathfelder.mynotes.shared.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class LoginMapperImpl extends RemoteServiceServlet implements LoginMapper { 
	
	
	
	public String login(User user) throws IllegalArgumentException {
		
		//do a database connection and check user information
		
		//send an answer back to client
		return "User: " + user.getUserName() + " is loggedin <br>"; //
	}



	
	
	
	
}
