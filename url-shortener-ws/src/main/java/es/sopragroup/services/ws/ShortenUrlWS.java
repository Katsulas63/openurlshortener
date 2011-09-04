package es.sopragroup.services.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.spi.resource.Singleton;

import es.sopragroup.core.entity.UrlShortable;

@Path("shortenURL")
@Singleton
public class ShortenUrlWS extends AbstractShortableUrlWS {

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
		UrlShortable url = new UrlShortable();
		url.setLongUrl(longUrl);
		
		// TODO algoritmo URL shortering
		url.setShortUrl(longUrl+"-corta");
		
		addUrl(url);
		return url.getShortUrl();
	}
	 
}