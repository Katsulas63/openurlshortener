package org.urlshortener.web.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("shorten")
public interface ShorteringUrlService extends RemoteService {
	String shortenUrlServer(String url) throws IllegalArgumentException;
}
