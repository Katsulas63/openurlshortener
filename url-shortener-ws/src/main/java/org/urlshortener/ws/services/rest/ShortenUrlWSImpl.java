package org.urlshortener.ws.services.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.urlshortener.core.dao.IUrlShortableDAO;
import org.urlshortener.core.entity.UrlShortable;
import org.urlshortener.core.util.UrlUtils;
import org.urlshortener.ws.services.IShortenUrlWS;

import com.sun.jersey.spi.resource.Singleton;


@Path("shortenURL")
@Component
@Singleton
public class ShortenUrlWSImpl implements IShortenUrlWS {

	@Autowired
	private IUrlShortableDAO urlShortableDAO;
	
	public ShortenUrlWSImpl() {
		super();
	}
	
	/**
	 * Genera la URL corta a partir de la larga y almacena ambas en BD 
	 * @param longUrl URL larga
	 * @return URL corta
	 */
	@GET
	@Path("{longURL}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String shortenURL(@PathParam("longURL") String longUrl) {		
		String result = "";
		longUrl = UrlUtils.decodeUrl(longUrl);
		final UrlShortable existingUrl = urlShortableDAO.getUrlByLongUrl(longUrl);		
		if (existingUrl != null) {
			result = existingUrl.getShortUrl();
		} else {
			UrlShortable url = new UrlShortable();
			url.setId(urlShortableDAO.getCountUrls().intValue());
			url.setLongUrl(longUrl);
			url.setShortUrl(UrlUtils.generateShortUrl(url.getId()));
			
			url = urlShortableDAO.saveUrl(url);	
			
			result = url.getShortUrl();
		}		
			
		return result;
	}
	
	/**
	 * @return the urlShortableDAO
	 */
	public IUrlShortableDAO getUrlShortableDAO() {
		return urlShortableDAO;
	}

	/**
	 * @param urlShortableDAO the urlShortableDAO to set
	 */
	public void setUrlShortableDAO(IUrlShortableDAO urlShortableDAO) {
		this.urlShortableDAO = urlShortableDAO;
	}
	 
}