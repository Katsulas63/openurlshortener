package es.sopragroup.services.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.spi.resource.Singleton;

import es.sopragroup.core.entity.UrlShortable;

@Path("expandURL")
@Singleton
public class ExpandUrlWS extends AbstractShortableUrlWS {

	public ExpandUrlWS() {
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
		
		// TODO get url from DB by shortURL
		UrlShortable url = urlMap.get(0);
		
		return url.getLongUrl();
	}
	
	 
}