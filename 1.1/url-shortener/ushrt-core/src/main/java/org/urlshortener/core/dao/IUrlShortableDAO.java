package org.urlshortener.core.dao;

import org.urlshortener.core.entity.UrlShortable;

/**
 * 
 * @author cyague
 *
 */
public interface IUrlShortableDAO {
	
	/**
	 * 
	 * @param shortUrl
	 * @return
	 */
	UrlShortable getUrlByShortUrl(String shortUrl);

	/**
	 * 
	 * @param longUrl
	 * @return
	 */
	UrlShortable getUrlByLongUrl(String longUrl);
	
	/**
	 * 
	 * @param url
	 * @return
	 */
	UrlShortable saveUrl(UrlShortable url);	
	
	/**
	 * 
	 * @return
	 */
	Long getCountUrls();
}
