package org.urlshortener.web.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("expand")
public interface ExpandingUrlService extends RemoteService {
	String expandUrlServer(String name) throws IllegalArgumentException;
}
