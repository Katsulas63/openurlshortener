package org.urlshortener.core.service;

/**
 * 
 * @author cyague
 *
 */
public interface IUrlShortenerWsManager extends IUrlShortableManager {
	
	/**
	 * 
	 * @param longUrl
	 * @param server
	 * @return
	 */
	String shortenURL(String longUrl, String server);
	
	/**
	 * 
	 * @param shortUrl
	 * @param server
	 * @return
	 */
	String expandURL(String shortUrl, String server);
	
}	
