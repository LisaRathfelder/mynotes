package com.lisarathfelder.mynotes.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.impl.AsyncFragmentLoader.Logger;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Mynotes implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side NoteMapper service.
	 */
	private final NoteMapperAsync NoteMapper = GWT.create(NoteMapper.class); //NoteMapper Objekt wird generiert. Über dieses Objekt können wir auf die Methoden von NoteMapper Implementation im Server zugreifen/Benutzen


	private  String username;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);


		final Label errorLabel = new Label();
		errorLabel.setText("Error Logs");
		errorLabel.addStyleName("errorLog"); //Style für CSS definieren

		final VerticalPanel loginPanel=new VerticalPanel();  
		final VerticalPanel notePanel=new VerticalPanel();
		final VerticalPanel editPanel=new VerticalPanel();

		loginPanel.setWidth("100%");
		loginPanel.setHeight("100%");
		loginPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		loginPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

		notePanel.setWidth("100%");
		notePanel.setHeight("100%");
		notePanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		notePanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

		editPanel.setWidth("100%");
		editPanel.setHeight("100%");
		editPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		editPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

		//Elemente von Login View
		final TextBox usernameField = new TextBox();
		usernameField.setText("Please type your username");
		final Button loginButton = new Button("Login");
		usernameField.getElement().setClassName("textField");
		loginButton.getElement().setClassName("button");

		loginPanel.add(usernameField);
		loginPanel.add(loginButton);


		//Elemente von Note View
		final Button createButton = new Button("Create note");
		createButton.getElement().setClassName("button");
		final TextBox noteText = new TextBox();
		noteText.setText("Saved Note1");
		final Button editButton = new Button("Edit");
		final Button deleteButton = new Button("Delete");
		final Button logoutButton = new Button("Logout");


		logoutButton.getElement().setClassName("button");

		notePanel.add(createButton);
		//notePanel.add(noteText);
		//notePanel.add(editButton);
		//notePanel.add(deleteButton);
		notePanel.add(logoutButton);



		//Elemente von Edit View
		final TextArea editText = new TextArea();//
		editText.setText("Type your Note / Existing Note comes here");
		editText.getElement().setClassName("textField");
		final Button saveButton = new Button("Save");
		saveButton.getElement().setClassName("button");

		editPanel.add(editText);
		editPanel.add(saveButton);

		// Use RootPanel.get() to get the entire body element
		RootPanel.get("mainContainer").add(loginPanel);
		RootPanel.get("mainContainer").add(notePanel);
		RootPanel.get("mainContainer").add(editPanel);
		RootPanel.get("errorContainer").add(errorLabel); //Error label in die Hauptseite (Rootpanel) hinzugefügt
		loginPanel.setVisible(true);
		notePanel.setVisible(false); //soll nicht sichtbar sein 
		editPanel.setVisible(false);

		// Event handlers:

		// Add a handler to login button
		loginButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				loginPanel.setVisible(false);
				notePanel.setVisible(true); //soll sichtbar sein 
				editPanel.setVisible(false);
				username=usernameField.getText();
				errorLabel.setText("Log: Saved Note1 of " + username);
				

			}
		});


		// Add a handler to logout button
		logoutButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				loginPanel.setVisible(true);
				notePanel.setVisible(false); //soll nicht sichtbar sein 
				editPanel.setVisible(false);
				//usernameField.setText(username);
				
				
			}
		});	

		// Add a handler to create button
		createButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				loginPanel.setVisible(false);
				notePanel.setVisible(false); //soll nicht sichtbar sein 
				editPanel.setVisible(true);
			}
		});	

		// Add a handler to save button
		saveButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				loginPanel.setVisible(false);
				notePanel.setVisible(true); //soll nicht sichtbar sein 
				editPanel.setVisible(false);
			
				NoteMapper.createNote(editText.getText(), 
						new AsyncCallback<String>() {
					public void onFailure(Throwable errorMessage) {
						// Show the RPC error message to the user
						errorLabel.setText("Error " + errorMessage);

					}

					public void onSuccess(String result) {
						dialogBox.setText("Successfull");
						serverResponseLabel.setHTML(result);
						dialogBox.center();
						closeButton.setFocus(true);
					}
				}
						);			
				
				
			}
		});	


		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});

	}
}
