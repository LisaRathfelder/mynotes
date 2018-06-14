package com.lisarathfelder.mynotes.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath; //GWT Klassen für RPC Kommunikation
import com.lisarathfelder.mynotes.shared.Note;
import com.lisarathfelder.mynotes.shared.User;

@RemoteServiceRelativePath("loginservice")
public interface LoginService extends RemoteService {
	String login(User user) throws IllegalArgumentException;
	String logout(User user) throws IllegalArgumentException;
	
	User createUser(User user) throws IllegalArgumentException;
	User getUserByUserName(String userName) throws IllegalArgumentException;
	
}
