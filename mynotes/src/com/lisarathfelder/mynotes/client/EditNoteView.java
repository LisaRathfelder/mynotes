package com.lisarathfelder.mynotes.client;

import java.util.Date;

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
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.lisarathfelder.mynotes.shared.Note;
import com.lisarathfelder.mynotes.shared.User;

public class EditNoteView {
	final VerticalPanel editNotePanel=new VerticalPanel();

	//Elemente von Edit View
	final TextBox noteTitle = new TextBox();
	final TextArea editText = new TextArea();
	final Button saveButton = new Button("Save");
	


	// Create the popup dialog box
	final DialogBox dialogBox = new DialogBox();
	final Button closeButton = new Button("Close");
	final HTML serverResponseLabel = new HTML();

	final Label errorLabel = new Label();


	/**
	 * Create a remote service proxy to talk to the server-side NoteMapper service.
	 */
	private final NoteServiceAsync NoteService = GWT.create(NoteService.class); //NoteMapper Objekt wird generiert. Über dieses Objekt können wir auf die Methoden von NoteMapper Implementation im Server zugreifen/Benutzen

	private Note currentNote = new Note();
	public void loadView(User user) {


		editNotePanel.setWidth("100%");
		editNotePanel.setHeight("100%");
		editNotePanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		editNotePanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

		editText.setText("Type your Note / Existing Note comes here");
		editText.getElement().setClassName("textArea");
		editText.setVisibleLines(10);
		noteTitle.getElement().setClassName("textFieldNote");
		noteTitle.setText("Type your note title");
		
		saveButton.getElement().setClassName("button");

		editNotePanel.add(noteTitle);
		editNotePanel.add(editText);
		editNotePanel.add(saveButton);

		RootPanel.get("mainContainer").clear();
		RootPanel.get("mainContainer").add(editNotePanel);
		
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
		
		// Add a handler to save button
		saveButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				//Save user data in currentNote object
				currentNote.setTitle(noteTitle.getText());
				currentNote.setContent(editText.getText());
			    Date currentDate = new Date();
				currentNote.setCreatDate(currentDate);
				currentNote.setModDate(currentDate);
				currentNote.setUserName(user.getUserName());

				
				//Sending the note object in server via RPC
				NoteService.createNote(currentNote, //RPC-Kommunikation
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
				
				
				// Loading Allnotesview
				AllNotesView allNotesView = new AllNotesView();
				allNotesView.loadView(user);	



			}
		});	


		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});
		
		noteTitle.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				noteTitle.setText("");
			}
		});

		editText.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				editText.setText("");
			}
		});

	}
}
