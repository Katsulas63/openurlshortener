package org.urlshortener.ws.services;


public interface IExpandUrlWS {
	
	/**
	 * Expands a short url
	 * @param shortUrl
	 * @return
	 */
	String expandURL(String shortUrl);
	 
}