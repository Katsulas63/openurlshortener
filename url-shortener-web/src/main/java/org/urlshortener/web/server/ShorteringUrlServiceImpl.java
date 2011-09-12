package org.urlshortener.web.server;

import org.urlshortener.core.service.IUrlShortenerWsManager;
import org.urlshortener.web.client.ShorteringUrlService;
import org.urlshortener.web.utils.UrlShortenerUtil;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ShorteringUrlServiceImpl extends RemoteServiceServlet implements
		ShorteringUrlService {

	public String shortenUrlServer(String longUrl) throws IllegalArgumentException {
		
		final IUrlShortenerWsManager urlShortenerWsManager = UrlShortenerUtil.getManager();
		
		if (urlShortenerWsManager == null) {
			return "Error: [urlShortenerWsManager] spring's instance is null";
		}
		
		final String shortUrl = urlShortenerWsManager.shortenURL(longUrl, UrlShortenerUtil.CLOUD_HTTP_SERVER);
		
		if (shortUrl != null) {
			return shortUrl;
		} else {
			return "Error: The return-value of shortenURL service is null";
		}
	}
	
}