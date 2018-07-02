package com.lisarathfelder.mynotes.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
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
    
	Crypto crypto= new Crypto();

	/**
	 * Create a remote service proxy to talk to the server-side NoteMapper service.
	 */
	private final LoginServiceAsync LoginService = GWT.create(LoginService.class); //LoginService Objekt wird generiert. Über dieses Objekt können wir auf die Methoden von LoginService Implementation im Server zugreifen/Benutzen



	private void createUser(User user) {
		//Methodenaufruf LoginService.createUser
		LoginService.createUser(user, //RPC-Kommunikation //Wenn User auf Login klickt,
				new AsyncCallback<User>() {
			public void onFailure(Throwable errorMessage) {
				// Show the RPC error message to the user
				errorLabel.setText("Error " + errorMessage);

			}

			public void onSuccess(User user) { 
				AllNotesView allNotesView = new AllNotesView(); //Wenn Login erfolgreich, wird die loadView geladen
				allNotesView.loadView(user);	
			}
		}
				);	
	}

	private void checkUser(User user) {
		//Methodenaufruf LoginService.initLoginMapperObject
		LoginService.initLoginMapperObject(new AsyncCallback<Void>() {
			public void onFailure(Throwable errorMessage) {
				// Show the RPC error message to the user
				errorLabel.setText("Error " + errorMessage);

			}

			@Override
			public void onSuccess(Void result) {
				// TODO Auto-generated method stub
				LoginService.getUserByUserName(user.getUserName(), //RPC-Kommunikation //Wenn User auf Login klickt,
						new AsyncCallback<User>() {
					public void onFailure(Throwable errorMessage) {
						// Show the RPC error message to the user
						errorLabel.setText("Error " + errorMessage);

					}

					public void onSuccess(User userfromDB) { 
						if (userfromDB.getUserID() == 0) {
							//user does not exist, create user in db
							createUser(user);

						}else {
							//user exists check password
							if(user.getuserPassword() == userfromDB.getuserPassword()) {
								//user has typed correct password 
								AllNotesView allNotesView = new AllNotesView(); //Wenn Login erfolgreich, wird die loadView geladen
								allNotesView.loadView(userfromDB);	
							}else {
								//wrong password
								errorLabel.setText("Error: Wrong Password ");
							}

						}


					}
				}
						);	

			}
		});

	}



	private void doLogin() {
		User currentUser = new User(); //neues User-Objekt wird erstellt
		currentUser.setUserName(usernameField.getText()); //Text von usernameField wird in currenUser gespeichert
		currentUser.setUserPassword(crypto.encryptString(passwordField.getText())); //Text von passwordField wird in currentUser gespeichert
		checkUser(currentUser);
	}

	public void loadView() {
		loginPanel.setWidth("100%");
		loginPanel.setHeight("100%");
		loginPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		loginPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

		usernameField.setText("Please type your username");
		usernameField.getElement().setClassName("textFieldLogin");
		passwordField.setText("1234");
		passwordField.getElement().setClassName("textFieldLogin"); 
		//loginButton.getElement().setClassName("button");
		loginButton.setStyleName("button");

		loginPanel.add(usernameField);
		loginPanel.add(passwordField);
		loginPanel.add(loginButton);

		RootPanel.get("mainContainer").clear();	
		RootPanel.get("mainContainer").add(loginPanel);

		errorLabel.setText("");
		errorLabel.addStyleName("errorLog"); //Style für CSS definieren
		RootPanel.get("errorContainer").clear();
		RootPanel.get("errorContainer").add(errorLabel); //Error label in die Hauptseite (Rootpanel) hinzugefügt


		// Add a handler to login button
		loginButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doLogin();
			}
		});


		class keyEnterHandler implements KeyDownHandler { //Definition //User drückt auf die Enter-Taste und Login erfolgt
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) { 
					doLogin();
				}
			}
		}	

		keyEnterHandler enterHandler = new keyEnterHandler();  //Instanzierung


		usernameField.addKeyDownHandler(enterHandler);
		passwordField.addKeyDownHandler(enterHandler);
		loginButton.addKeyDownHandler(enterHandler);
		loginButton.setFocus(true);



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
