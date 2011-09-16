package org.urlshortener.web.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface ExpandingUrlServiceAsync {
	void expandUrlServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
}
