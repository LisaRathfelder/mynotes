package com.lisarathfelder.mynotes.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.lisarathfelder.mynotes.shared.User;

public class LoginView {

	final VerticalPanel loginPanel=new VerticalPanel();  
	//Elemente von Login View
	final TextBox usernameField = new TextBox();
	final PasswordTextBox passwordField = new PasswordTextBox(); 
	final Button loginButton = new Button("Login");
	final Label errorLabel = new Label();
	

	// Create the popup dialog box
	final DialogBox dialogBox = new DialogBox();
	final Button closeButton = new Button("Close");
	final HTML serverResponseLabel = new HTML();
	private  String username;

	private User currentUser = new User();

	/**
	 * Create a remote service proxy to talk to the server-side NoteMapper service.
	 */
	private final LoginServiceAsync LoginService = GWT.create(LoginService.class); //LoginMapper Objekt wird generiert. Über dieses Objekt können wir auf die Methoden von LoginMapper Implementation im Server zugreifen/Benutzen



	public void loadView() {
		loginPanel.setWidth("100%");
		loginPanel.setHeight("100%");
		loginPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		loginPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

		usernameField.setText("Please type your username");
		usernameField.getElement().setClassName("textFieldLogin");
		passwordField.setText("1234");
		passwordField.getElement().setClassName("textFieldLogin"); 
		loginButton.getElement().setClassName("button");

		loginPanel.add(usernameField);
		loginPanel.add(passwordField);
		loginPanel.add(loginButton);

		RootPanel.get("mainContainer").clear();	
		RootPanel.get("mainContainer").add(loginPanel);

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

		// Add a handler to login button
		loginButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {


				username=usernameField.getText();

				currentUser.setUserName(usernameField.getText());

				errorLabel.setText("Log: Saved Note1 of " + username);

				LoginService.login(currentUser, //RPC-Kommunikation
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
				
				AllNotesView allNotesView = new AllNotesView();
				allNotesView.loadView(currentUser);	



			}
		});

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});
		
		passwordField.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				passwordField.setText("");	
			}
		});
	
		usernameField.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				usernameField.setText("");	
			}
		});
		
		
	
	
	}
}
