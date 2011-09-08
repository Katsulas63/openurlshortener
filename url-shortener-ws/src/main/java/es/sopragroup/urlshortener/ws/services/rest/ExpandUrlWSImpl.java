package es.sopragroup.urlshortener.ws.services.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.jersey.spi.resource.Singleton;

import es.sopragroup.urlshortener.core.dao.IUrlShortableDAO;
import es.sopragroup.urlshortener.core.entity.UrlShortable;
import es.sopragroup.urlshortener.core.util.UrlUtils;
import es.sopragroup.urlshortener.ws.services.IExpandUrlWS;

@Component
@Singleton
@Path("expandURL")
public class ExpandUrlWSImpl implements IExpandUrlWS {
	
	@Autowired
	private IUrlShortableDAO urlShortableDAO;
	
	public ExpandUrlWSImpl() {
		super();
	}
	
	/**
	 * Obtiene de BD la url larga a partir de la corta
	 * @param shortUrl URL corta
	 * @return URL larga
	 */
	@GET
	@Path("{shortURL}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String expandURL(@PathParam("shortURL")String shortUrl) {				
		String result = "";
		final UrlShortable url = this.getUrlShortableDAO().getUrlByShortUrl(UrlUtils.decodeUrl(shortUrl));
		
		if (url != null) {
			result = url.getLongUrl();
		} else {
			result = "ERROR: The specified short-url cannot be expanded.";
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