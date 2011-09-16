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
@Path("shortenURL")
@Component
@Singleton
public class ShortenUrlWS extends AbstractUrlWS {
	
	/**
	 * constructors<br>
	 * ============
	 */
	
	public ShortenUrlWS() {
		super();
	}
	
	/**
	 * public methods<br>
	 * ==============
	 */
	
	/**
	 * Shorten a long-url
	 * 
	 * @param longUrl
	 * @return
	 */
	@GET
	@Path("{longURL}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String shortenURL(@PathParam("longURL") String longUrl) {		
		final String result = manager.shortenURL(longUrl);
		return result;
	}
	 
}