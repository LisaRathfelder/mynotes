package com.lisarathfelder.mynotes.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath; //GWT Klassen für RPC Kommunikation

@RemoteServiceRelativePath("notemapper")
public interface NoteMapper extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;
}
