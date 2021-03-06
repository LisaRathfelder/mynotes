package com.lisarathfelder.mynotes.client;


import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.lisarathfelder.mynotes.shared.Note;
import com.lisarathfelder.mynotes.shared.User;

public class AllNotesView {

	final VerticalPanel allNotesPanel=new VerticalPanel();
	final FlexTable userNotesTable=new FlexTable();
	//Elemente von All Notes View
	final Button createButton = new Button("Create note");
	final Button logoutButton = new Button("Logout");
	final Label errorLabel = new Label();

	private final NoteServiceAsync NoteService = GWT.create(NoteService.class); //NoteService (Proxy) Objekt wird generiert. Über dieses Objekt können wir auf die Methoden von NoteService Implementation im Server zugreifen

	public void loadView(User user) {

		//1. Über das Proxy-Objekt wird die Methode/Service getAllNoteUser aufgerufen
		//Methodenaufruf NoteService.initNoteMapperObject
		NoteService.initNoteMapperObject(new AsyncCallback<Void>() {
			public void onFailure(Throwable errorMessage) {
				// Show the RPC error message to the user
				errorLabel.setText("Error " + errorMessage);

			}

			@Override
			public void onSuccess(Void result) {
				//Methodenaufruf getAllNotesUser
				NoteService.getAllNotesUser(user, //RPC-Kommunikation
						new AsyncCallback<ArrayList<Note>>() {
					public void onFailure(Throwable errorMessage) {
						// Show the RPC error message to the user
						errorLabel.setText("Error " + errorMessage);

					}
					//2. Antwort vom Server
					public void onSuccess(ArrayList<Note> result) {

						for (int i=0; i<result.size();i++) {
							Note note=new Note();
							note=result.get(i);
							final Label noteTitle = new Label();
							final Button editButton = new Button("Edit");
							final Button deleteButton = new Button("Delete");
							noteTitle.setText(note.getTitle());
							noteTitle.getElement().setClassName("noteTitle");
							deleteButton.getElement().setClassName("deleteButton");
							deleteButton.getElement().setId(String.valueOf(note.getNoteID())); //deleteButton bekommt ID
							editButton.getElement().setId(String.valueOf(note.getNoteID()));//editButton bekommt ID
							editButton.getElement().setClassName("editButton");
							deleteButton.addClickHandler(
									new ClickHandler() {
										public void onClick(ClickEvent event) {
											//Delete user data in currentNote object - Methodenaufruf deleteNoteOfId
											Button currentButton= (Button) event.getSource(); //event source is type casted to a button
											NoteService.deleteNoteOfId(Integer.parseInt(currentButton.getElement().getId()), 

													new AsyncCallback<String>() {
												public void onFailure(Throwable errorMessage) {
													// Show the RPC error message to the user
													errorLabel.setText("Error " + errorMessage);
												}
												public void onSuccess(String result) {
													errorLabel.setText("Note: Deleted");
													AllNotesView allNotesView = new AllNotesView();
													allNotesView.loadView(user);	
												}
											}			
													); //end of deleteNoteofId

										}
									}		

									);// end of addClickHandler
							editButton.addClickHandler(

									new ClickHandler() {
										public void onClick(ClickEvent event) {
											//Edit user data in currentNote object
											Button currentButton= (Button) event.getSource();
											EditNoteView editNoteView = new EditNoteView();
											editNoteView.loadView(user,Integer.parseInt(currentButton.getElement().getId()));	


										}	} 



									);					//addclickhandler of edit button 



							userNotesTable.setWidget(i, 0, noteTitle);
							userNotesTable.setWidget(i, 1, editButton);
							userNotesTable.setWidget(i, 2, deleteButton);

							userNotesTable.setStyleName("userTable");
							userNotesTable.getFlexCellFormatter().setStyleName(0, 0, "tableCell-title");  
							userNotesTable.getFlexCellFormatter().setStyleName(0, 1, "tableCell-button");  
							userNotesTable.getFlexCellFormatter().setStyleName(0, 2, "tableCell-button");  

						} //end of for loop
						loadGui(user);
					}
				}
						);				
			}
		});


	}

	private void loadGui(User user) {

		allNotesPanel.setWidth("100%");
		allNotesPanel.setHeight("100%");
		allNotesPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		allNotesPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);


		createButton.getElement().setClassName("button");
		logoutButton.getElement().setClassName("button");

		allNotesPanel.add(createButton);
		allNotesPanel.add(userNotesTable);
		allNotesPanel.add(logoutButton);

		RootPanel.get("mainContainer").clear();	
		RootPanel.get("mainContainer").add(allNotesPanel);

		errorLabel.setText("");
		errorLabel.addStyleName("errorLog"); //Style für CSS definieren
		RootPanel.get("errorContainer").clear();
		RootPanel.get("errorContainer").add(errorLabel); //Error label in die Hauptseite (Rootpanel) hinzugefügt


		// Add a handler to logout button
		//LoginService.logout - Methodenaufruf
		logoutButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				LoginView loginView = new LoginView();
				loginView.loadView();				

			}
		});	

		// Add a handler to create button
		createButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				EditNoteView editNoteView = new EditNoteView();
				editNoteView.loadView(user,0); //id 0 = create note					
			}
		});	


	}
}
