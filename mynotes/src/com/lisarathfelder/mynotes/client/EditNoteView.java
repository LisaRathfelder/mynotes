package com.lisarathfelder.mynotes.client;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
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


	final Label errorLabel = new Label();


	/**
	 * Create a remote service proxy to talk to the server-side NoteService.
	 */
	private final NoteServiceAsync NoteService = GWT.create(NoteService.class); //NoteService Objekt wird generiert. Über dieses Objekt können wir auf die Methoden von NoteService Implementation im Server zugreifen


	private void loadGui(User user, Note note) {
		editNotePanel.setWidth("100%");
		editNotePanel.setHeight("100%");
		editNotePanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		editNotePanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

		editText.getElement().setClassName("textArea"); //Styles den Elementen hinzugefügt
		editText.setVisibleLines(10);
		noteTitle.getElement().setClassName("textFieldNote");

		if(note.getNoteID()==0) {  //Wenn User den create-Button klickt wird editNotView mit id=0 geladen
			//create Note
			editText.setText("Type your note");
			noteTitle.setText("Type your note title");
		}else {
			//edit Note
			editText.setText(note.getContent()); //Wenn User den editButton klickt wird die editNoteView mit der NoteId aufgerufen
			noteTitle.setText(note.getTitle());
		}

		saveButton.getElement().setClassName("button");

		editNotePanel.add(noteTitle); //GUI-Elemente zum Panel hinzugefügt
		editNotePanel.add(editText);
		editNotePanel.add(saveButton);

		RootPanel.get("mainContainer").clear();
		RootPanel.get("mainContainer").add(editNotePanel); //editNotePanel dem mainContainer hinzugefügt 

		errorLabel.setText("");
		errorLabel.addStyleName("errorLog"); //Style für CSS definieren
		RootPanel.get("errorContainer").clear();
		RootPanel.get("errorContainer").add(errorLabel); //Error label in die Hauptseite (Rootpanel) hinzugefügt


		// Add a handler to save button - Hier wird dem Save Button ein ClickHandler hinzugefügt und ein Note Objekt erstellt 
		saveButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				Note currentNote = new Note(); //leeres Notiz-Objekt wird erstellt
				currentNote.setTitle(noteTitle.getText()); //Inhalt von noteTitle und editText wird übergeben
				currentNote.setContent(editText.getText());
				Date currentDate = new Date(); //neues Date Objekt wird erstellt
				currentNote.setModDate(currentDate); 
				currentNote.setUserName(user.getUserName()); //UserName wird in currentNote gespeichert
				currentNote.setNoteID(note.getNoteID());

				if(note.getNoteID()==0) { 
					//create note - Die Notiz wird erstellt
					currentNote.setCreatDate(currentDate); //creatDate wird in currentNote gespeichert
					//Sending the note object in the server via RPC
					NoteService.createNote(currentNote, //RPC-Kommunikation
							new AsyncCallback<String>() {
						public void onFailure(Throwable errorMessage) {
							// Show the RPC error message to the user
							errorLabel.setText("Error " + errorMessage);
						}
						public void onSuccess(String result) { //Wenn DB connection hergestellt wurde und Notiz gespeichert wurde wird die AllNotesView geladen
							AllNotesView allNotesView = new AllNotesView();
							allNotesView.loadView(user);	
						}
					}
							); //end if createNote

				}else { 
					//edit note - Eine bestehende Notiz wird anhand der ID des currentNote Objekts editiert
					NoteService.editNote(currentNote, //ID des curretNote Objekts wird aufgerufen
							new AsyncCallback<String>() {
						public void onFailure(Throwable errorMessage) {
							// Show the RPC error message to the user
							errorLabel.setText("Error " + errorMessage);
						}
						public void onSuccess(String result) { //Wenn DB connection besteht und Notiz editiert wurde wird AllNotesView geladen
							AllNotesView allNotesView = new AllNotesView();
							allNotesView.loadView(user);	
						}
					}		
							);//end of editnote
				}
			}// end if onClick
		});	


		noteTitle.addClickHandler(new ClickHandler(){ //ClickHandler der ermöglicht einen Text in den Titel zu tippen
			public void onClick(ClickEvent event) {
				if(note.getNoteID()==0) { //create note
					noteTitle.setText("");	
				}

			}
		});

		editText.addClickHandler(new ClickHandler(){ //ClickHandler der ermöglicht einen Text in das Textfeld zu tippen
			public void onClick(ClickEvent event) {
				if(note.getNoteID()==0) { //create note
					editText.setText("");
				}

			}
		});

	}// end of loadgui

	public void loadView(User user, int nId) {

		if (nId==0) {
			//create note
			Note emptyNote = new Note(); //Warum hier nochmal emptyNote wenn wir oben schon currentNote haben?
			emptyNote.setNoteID(0);
			loadGui(user,emptyNote);
		}else {
			//edit note
			// first get the note title and content from the database
			NoteService.getNoteOfID(nId, 
					//RPC-Kommunikation
					new AsyncCallback<Note>() {
				public void onFailure(Throwable errorMessage) {
					// Show the RPC error message to the user
					errorLabel.setText("Error " + errorMessage);
				}

				public void onSuccess(Note note) {
					loadGui(user,note);
				}
			}
					);	
		}







	}
}
