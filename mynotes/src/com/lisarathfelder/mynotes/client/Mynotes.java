package com.lisarathfelder.mynotes.client;

import com.google.gwt.core.client.EntryPoint;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Mynotes implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		LoginView loginView = new LoginView();
		loginView.loadView();
		

}
}
