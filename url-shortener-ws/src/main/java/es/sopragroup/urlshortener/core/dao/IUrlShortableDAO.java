package es.sopragroup.urlshortener.core.dao;

import es.sopragroup.urlshortener.core.entity.UrlShortable;

public interface IUrlShortableDAO {
	
	UrlShortable saveUrl(UrlShortable url);	
	
	Long getCountUrls();

	UrlShortable getUrlByShortUrl(String shortUrl);

	UrlShortable getUrlByLongUrl(String longUrl);
}
