package com.lisarathfelder.mynotes.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.lisarathfelder.mynotes.shared.User;

public interface LoginServiceAsync {

	void createUser(User user, AsyncCallback<User> callback);

	void getUserByUserName(String userName, AsyncCallback<User> callback);

	void initLoginMapperObject(AsyncCallback<Void> callback);

}
