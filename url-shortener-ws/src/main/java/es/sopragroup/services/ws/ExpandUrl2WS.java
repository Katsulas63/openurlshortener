package es.sopragroup.services.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.spi.inject.Inject;
import com.sun.jersey.spi.resource.Singleton;

import es.sopragroup.core.dao.IUrlShortableDAO;
import es.sopragroup.core.entity.UrlShortable;
import es.sopragroup.core.util.UrlUtil;

@Path("expandURL2")
@Singleton
public class ExpandUrl2WS extends AbstractShortableUrlWS {

	@Inject
	private IUrlShortableDAO urlShortableDAO;
	
	public ExpandUrl2WS() {
		super();
	}
	
	/**
	 * Obtiene de BD la url larga a partir de la corta
	 * @param shortUrl URL corta
	 * @return URL larga
	 */
	@GET
	@Path("{shortURL}")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public UrlShortable expandURL(@PathParam("shortURL")String shortUrl) {
		final UrlShortable url = this.getUrlShortableDAO().getUrlByShortUrl(UrlUtil.decodeUrl(shortUrl));		
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