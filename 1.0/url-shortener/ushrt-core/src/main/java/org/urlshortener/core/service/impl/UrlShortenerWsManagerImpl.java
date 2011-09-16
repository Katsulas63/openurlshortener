package org.urlshortener.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.urlshortener.core.dao.IUrlShortenerWsDAO;
import org.urlshortener.core.service.IUrlShortenerWsManager;
import org.urlshortener.core.util.UrlUtils;

/**
 * 
 * @author cyague
 *
 */
public class UrlShortenerWsManagerImpl implements IUrlShortenerWsManager {
	
	/**
	 * DAOs<br>
	 * ====
	 */
	
	@Autowired
	private IUrlShortenerWsDAO dao;
	
	/**
	 * public methods<br>
	 * ==============
	 */

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String shortenURL(String longUrl) {
		final String encodeLongUrl = UrlUtils.encodeUrl(longUrl);
		return dao.shortenUrl(encodeLongUrl);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String shortenURL(String longUrl, String server) {
		final String encodeLongUrl = UrlUtils.encodeUrl(longUrl);
		return dao.shortenUrl(encodeLongUrl, server);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String expandURL(String shortUrl) {
		final String encodeShortUrl = UrlUtils.encodeUrl(shortUrl);
		return dao.expandUrl(encodeShortUrl);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String expandURL(String shortUrl, String server) {
		final String encodeShortUrl = UrlUtils.encodeUrl(shortUrl);
		return dao.expandUrl(encodeShortUrl, server);
	}
	
	/**
	 * getter & setters<br>
	 * ================
	 */

	/**
	 * @return the dao
	 */
	public IUrlShortenerWsDAO getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(IUrlShortenerWsDAO dao) {
		this.dao = dao;
	}
	
}
