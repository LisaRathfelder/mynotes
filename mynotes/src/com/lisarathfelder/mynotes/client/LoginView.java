package com.lisarathfelder.mynotes.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LoginView {

	final VerticalPanel loginPanel=new VerticalPanel();  
	//Elemente von Login View
	final TextBox usernameField = new TextBox();
	final Button loginButton = new Button("Login");
	final Label errorLabel = new Label();

	private  String username;



	public void loadView() {
		loginPanel.setWidth("100%");
		loginPanel.setHeight("100%");
		loginPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		loginPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

		usernameField.setText("Please type your username");
		usernameField.getElement().setClassName("textField");
		loginButton.getElement().setClassName("button");

		loginPanel.add(usernameField);
		loginPanel.add(loginButton);

		RootPanel.get("mainContainer").clear();	
		RootPanel.get("mainContainer").add(loginPanel);
		
		errorLabel.setText("Error Logs");
		errorLabel.addStyleName("errorLog"); //Style für CSS definieren
		RootPanel.get("errorContainer").clear();
		RootPanel.get("errorContainer").add(errorLabel); //Error label in die Hauptseite (Rootpanel) hinzugefügt
		
		// Add a handler to login button
		loginButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				AllNotesView allNotesView = new AllNotesView();
				allNotesView.loadView();	
				username=usernameField.getText();
				errorLabel.setText("Log: Saved Note1 of " + username);


			}
		});


	}

}
