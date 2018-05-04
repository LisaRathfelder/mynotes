package com.lisarathfelder.mynotes.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.lisarathfelder.mynotes.shared.User;

public interface LoginMapperAsync {

	void login(User user, AsyncCallback<String> callback);

}
