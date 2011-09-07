package es.sopragroup.services.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.jersey.spi.resource.Singleton;

import es.sopragroup.core.dao.IUrlShortableDAO;
import es.sopragroup.core.entity.UrlShortable;
import es.sopragroup.core.util.UrlUtils;

@Path("shortenURL")
@Component
@Singleton
public class ShortenUrlWS {

	@Autowired
	private IUrlShortableDAO urlShortableDAO;
	
	public ShortenUrlWS() {
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
			result = "WARNING: This long-url has been already shorted to: "+existingUrl.getShortUrl();
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