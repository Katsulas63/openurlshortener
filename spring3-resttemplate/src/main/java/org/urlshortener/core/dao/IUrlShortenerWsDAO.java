
package org.urlshortener.core.dao;

public interface IUrlShortenerWsDAO {

	/**
	 * 
	 * @param shortUrl
	 * @return
	 */
	String expandUrl(String shortUrl);

	/**
	 * 
	 * @param shortUrl
	 * @param server
	 * @return
	 */
	String expandUrl(String shortUrl, String server);

	/**
	 * 
	 * @param longUrl
	 * @return
	 */
	String shortenUrl(String longUrl);

	/**
	 * 
	 * @param longUrl
	 * @param server
	 * @return
	 */
	String shortenUrl(String longUrl, String server);

}
