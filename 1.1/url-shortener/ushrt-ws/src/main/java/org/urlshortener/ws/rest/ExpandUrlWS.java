package org.urlshortener.ws.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.sun.jersey.spi.resource.Singleton;

/**
 * 
 * @author cyague
 *
 */
@Component
@Singleton
@Path("expandURL")
public class ExpandUrlWS extends AbstractUrlWS {

	/**
	 * constructors<br>
	 * ============
	 */
	
	public ExpandUrlWS() {
		super();
	}
	
	/**
	 * public methods<br>
	 * ==============
	 */
	
	/**
	 * Expands a short-url
	 * 
	 * @param shortUrl
	 * @return
	 */
	@GET
	@Path("{shortURL}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String expandURL(@PathParam("shortURL")String shortUrl) {						
		final String result = manager.expandURL(shortUrl);		
		return result;
	}

}