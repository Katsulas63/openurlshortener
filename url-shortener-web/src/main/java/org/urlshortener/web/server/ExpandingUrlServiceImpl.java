package org.urlshortener.web.server;

import org.urlshortener.core.service.IUrlShortenerWsManager;
import org.urlshortener.web.client.ExpandingUrlService;
import org.urlshortener.web.utils.UrlShortenerUtil;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ExpandingUrlServiceImpl extends RemoteServiceServlet implements
		ExpandingUrlService {

	public String expandUrlServer(String shortUrl) throws IllegalArgumentException {
		
		IUrlShortenerWsManager urlShortenerWsManager = UrlShortenerUtil.getManager();
		
		if (urlShortenerWsManager == null) {
			return "Error: [urlShortenerWsManager] spring's instance is null";
		}
		
		final String longUrl = urlShortenerWsManager.expandURL(shortUrl, UrlShortenerUtil.CLOUD_HTTP_SERVER);
		
		if (longUrl != null) {
			return longUrl;
		} else {
			return "Error: The return-value of expandURL service is null";
		}
	}
	
}