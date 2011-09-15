package org.urlshortener.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.urlshortener.core.dao.IUrlShortableDAO;
import org.urlshortener.core.entity.UrlShortable;
import org.urlshortener.core.service.IUrlShortableManager;
import org.urlshortener.core.util.UrlUtils;

/**
 * 
 * @author cyague
 *
 */
public class UrlShortableManagerImpl implements IUrlShortableManager {
	
	/**
	 * DAOs<br>
	 * ====
	 */
	
	@Autowired
	private IUrlShortableDAO dao;
	
	/**
	 * public methods<br>
	 * ==============
	 */

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String shortenURL(String longUrl) {
		String result = "";
		longUrl = UrlUtils.decodeUrl(longUrl);
		final UrlShortable existingUrl = dao.getUrlByLongUrl(longUrl);		
		if (existingUrl != null) {
			result = existingUrl.getShortUrl();
		} else {
			UrlShortable url = new UrlShortable();
			url.setId(dao.getCountUrls().intValue());
			url.setLongUrl(longUrl);
			url.setShortUrl(UrlUtils.generateShortUrl(url.getId()));
			
			url = dao.saveUrl(url);	
			
			result = url.getShortUrl();
		}		
			
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String expandURL(String shortUrl) {
		String result = "";
		final UrlShortable url = dao.getUrlByShortUrl(UrlUtils.decodeUrl(shortUrl));
		
		if (url != null) {
			result = url.getLongUrl();
		} else {
			result = "ERROR: The specified short-url cannot be expanded.";
		}
		
		return result;
	}
	
	/**
	 * getter & setters<br>
	 * ================
	 */
	
	/**
	 * @return the dao
	 */
	public IUrlShortableDAO getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(IUrlShortableDAO dao) {
		this.dao = dao;
	}

	
}
