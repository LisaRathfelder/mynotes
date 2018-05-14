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
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.lisarathfelder.mynotes.shared.User;

public class AllNotesView {

	final VerticalPanel allNotesPanel=new VerticalPanel();
	//Elemente von Note View
	final Button createButton = new Button("Create note");
	//final TextBox noteTitle = new TextBox();
	final TextBox noteText = new TextBox();
	final Button editButton = new Button("Edit");
	final Button deleteButton = new Button("Delete");
	final Button logoutButton = new Button("Logout");

	final Label errorLabel = new Label();
	
	// Create the popup dialog box
	final DialogBox dialogBox = new DialogBox();
	final Button closeButton = new Button("Close");
	final HTML serverResponseLabel = new HTML();

	private final LoginServiceAsync LoginService = GWT.create(LoginService.class); //LoginMapper (Proxy) Objekt wird generiert. Über dieses Objekt können wir auf die Methoden von LoginMapper Implementation im Server zugreifen/Benutzen


	public void loadView(User user) {

		allNotesPanel.setWidth("100%");
		allNotesPanel.setHeight("100%");
		allNotesPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		allNotesPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

		createButton.getElement().setClassName("button");
		noteText.setText("Saved Note1");
		logoutButton.getElement().setClassName("button");

		allNotesPanel.add(createButton);
		//allNotesPanel.add(noteText);
		//allNotesPanel.add(editButton);
		//allNotesPanel.add(deleteButton);
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
							dialogBox.center();
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

	}
}
