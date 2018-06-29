package com.lisarathfelder.mynotes.server;

import com.lisarathfelder.mynotes.client.LoginService;
import com.lisarathfelder.mynotes.shared.User;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService { 
	
	
	  
	  /**
		 * Referenz auf den DatenbankMapper, der Userobjekte mit der Datenbank
		 * abgleicht.
		 */
	private LoginMapper lMapper = null;
	
	public void initLoginMapperObject() throws IllegalArgumentException {

	    this.lMapper = LoginMapper.loginMapper();
	    
	}
	
	@Override
	public User createUser(User user) throws IllegalArgumentException {
		
		return this.lMapper.createUser(user);
	
	}

	@Override
	public User getUserByUserName(String userName) throws IllegalArgumentException {
		return this.lMapper.getUserByUserName(userName);
			
		
		
	}


	
	
	
}
