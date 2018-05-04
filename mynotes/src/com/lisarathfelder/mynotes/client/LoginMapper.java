package com.lisarathfelder.mynotes.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath; //GWT Klassen für RPC Kommunikation
import com.lisarathfelder.mynotes.shared.User;

@RemoteServiceRelativePath("loginmapper")
public interface LoginMapper extends RemoteService {
	String login(User user) throws IllegalArgumentException;
}
