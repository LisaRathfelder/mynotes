package com.lisarathfelder.mynotes.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.lisarathfelder.mynotes.shared.User;

public interface LoginServiceAsync {

	void login(User user, AsyncCallback<String> callback);

	void logout(User user, AsyncCallback<String> callback);

}
