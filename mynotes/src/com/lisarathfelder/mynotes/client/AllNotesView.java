package com.lisarathfelder.mynotes.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.lisarathfelder.mynotes.shared.Note;
import com.lisarathfelder.mynotes.shared.User;

public class AllNotesView {

	final VerticalPanel allNotesPanel=new VerticalPanel();
	final FlexTable userNotesTable=new FlexTable();
	//Elemente von Note View
	final Button createButton = new Button("Create note");
	final Label noteTitle = new Label();
	final Button editButton = new Button("Edit");
	final Button deleteButton = new Button("Delete");
	final Button logoutButton = new Button("Logout");


	final Label errorLabel = new Label();

	// Create the popup dialog box
	final DialogBox dialogBox = new DialogBox();
	final Button closeButton = new Button("Close");
	final HTML serverResponseLabel = new HTML();

	private final LoginServiceAsync LoginService = GWT.create(LoginService.class); //LoginService (Proxy) Objekt wird generiert. Über dieses Objekt können wir auf die Methoden von LoginService Implementation im Server zugreifen/Benutzen
	private final NoteServiceAsync NoteService = GWT.create(NoteService.class); //NoteService (Proxy) Objekt wird generiert. Über dieses Objekt können wir auf die Methoden von NoteService Implementation im Server zugreifen/Benutzen

	public void loadView(User user) {

		allNotesPanel.setWidth("100%");
		allNotesPanel.setHeight("100%");
		allNotesPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		allNotesPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

		//userNotesTable.setWidth("50%");
		//userNotesTable.setHeight("100%");


		createButton.getElement().setClassName("button");
		noteTitle.setText("Saved Note1");
		noteTitle.getElement().setClassName("noteTitle");
		deleteButton.getElement().setClassName("deleteButton");
		logoutButton.getElement().setClassName("button");
		editButton.getElement().setClassName("editButton");


		allNotesPanel.add(createButton);

		userNotesTable.setWidget(0, 0, noteTitle);
		userNotesTable.setWidget(0, 1, editButton);
		userNotesTable.setWidget(0, 2, deleteButton);







		allNotesPanel.add(userNotesTable);


		NoteService.getAllNotesUser(user, //RPC-Kommunikation
				new AsyncCallback<ArrayList<Note>>() {
			public void onFailure(Throwable errorMessage) {
				// Show the RPC error message to the user
				errorLabel.setText("Error " + errorMessage);

			}

			public void onSuccess(ArrayList<Note> result) {
				dialogBox.setText("Successfull");
				String dummy="";
				for (int i=0; i<result.size();i++) {
					Note note=new Note();
					note=result.get(i);
					dummy+=note.getTitle();
					dummy= dummy + "<br>";
				}
				serverResponseLabel.setHTML(dummy);


				dialogBox.center();
				closeButton.setFocus(true);
			}
		}
				);



		allNotesPanel.add(logoutButton);

		RootPanel.get("mainContainer").clear();	
		RootPanel.get("mainContainer").add(allNotesPanel);

		errorLabel.setText("Error Logs");
		errorLabel.addStyleName("errorLog"); //Style für CSS definieren
		RootPanel.get("errorContainer").clear();
		RootPanel.get("errorContainer").add(errorLabel); //Error label in die Hauptseite (Rootpanel) hinzugefügt

		dialogBox.setText("");
		dialogBox.setAnimationEnabled(true);
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);		

		// Add a handler to logout button
		logoutButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				LoginService.logout(user,  //Proxy Object
						new AsyncCallback<String>() {
					public void onFailure(Throwable errorMessage) {
						// Show the RPC error message to the user
						errorLabel.setText("Error " + errorMessage);
					}
					public void onSuccess(String result) {
						dialogBox.setText("Successfull");
						serverResponseLabel.setHTML(result);
						//dialogBox.center();
						closeButton.setFocus(true);
					}
				}			

						);
				LoginView loginView = new LoginView();
				loginView.loadView();				

			}
		});	

		// Add a handler to create button
		createButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				EditNoteView editNoteView = new EditNoteView();
				editNoteView.loadView(user);					
			}
		});	

		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide(); 
			}
		});

		// Add a handler to delete button
		deleteButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				//Delete user data in currentNote object
			NoteService.deleteNoteOfId(4, 
					
					new AsyncCallback<String>() {
				public void onFailure(Throwable errorMessage) {
					// Show the RPC error message to the user
					errorLabel.setText("Error " + errorMessage);
				}
				public void onSuccess(String result) {
					errorLabel.setText("Note: Deleted");
				}
			}			
		
					
					
					
					
					
					);
			
			

			}
		});	
	}
}
