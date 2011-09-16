package org.urlshortener.core.service;

/**
 * 
 * @author cyague
 *
 */
public interface IUrlShortableManager {
	
	/**
	 * 
	 * @param longUrl
	 * @return
	 */
	String shortenURL(String longUrl);
	
	/**
	 * 
	 * @param shortUrl
	 * @return
	 */
	String expandURL(String shortUrl);
	
}	
