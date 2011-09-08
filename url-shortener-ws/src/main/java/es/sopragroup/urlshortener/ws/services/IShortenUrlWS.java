package es.sopragroup.urlshortener.ws.services;


public interface IShortenUrlWS {

	/**
	 * Shorten an long url
	 * @param longUrl
	 * @return
	 */
	String shortenURL(String longUrl);
	 
}