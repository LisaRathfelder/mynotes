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

public class AllNotesView {

	final VerticalPanel allNotesPanel=new VerticalPanel();
	//Elemente von Note View
	final Button createButton = new Button("Create note");
	final TextBox noteText = new TextBox();
	final Button editButton = new Button("Edit");
	final Button deleteButton = new Button("Delete");
	final Button logoutButton = new Button("Logout");

	final Label errorLabel = new Label();




	public void loadView() {

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
		
		// Add a handler to logout button
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
				editNoteView.loadView();					
			}
		});	



	}
}
