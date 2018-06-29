package com.lisarathfelder.mynotes.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath; //GWT Klassen für RPC Kommunikation
import com.lisarathfelder.mynotes.shared.User;

@RemoteServiceRelativePath("loginservice")
public interface LoginService extends RemoteService {
	  
	User createUser(User user) throws IllegalArgumentException;
	User getUserByUserName(String userName) throws IllegalArgumentException;
	void initLoginMapperObject() throws IllegalArgumentException;

}
