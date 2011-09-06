package es.sopragroup.services.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.sun.jersey.spi.resource.Singleton;

import es.sopragroup.core.dao.IUrlShortableDAO;
import es.sopragroup.core.entity.UrlShortable;
import es.sopragroup.core.util.UrlUtil;

@Path("shortenURL2")
@Singleton
public class ShortenUrl2WS extends AbstractShortableUrlWS {

	@Autowired
	private IUrlShortableDAO urlShortableDAO;
	
	public ShortenUrl2WS() {
		super();
	}
	
	/**
	 * Genera la URL corta a partir de la larga y almacena ambas en BD 
	 * @param longUrl URL larga
	 * @return URL corta
	 */
	@GET
	@Path("{longURL}")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public UrlShortable shortenURL(@PathParam("longURL") String longUrl) {
		UrlShortable url = new UrlShortable();
		url.setLongUrl(longUrl);
		
		final String shortUrl = UrlUtil.generateShortUrl(UrlUtil.decodeUrl(longUrl));
		url.setShortUrl(shortUrl);
		
		this.getUrlShortableDAO().saveUrl(url);
		return url;
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