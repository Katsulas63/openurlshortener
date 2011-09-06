package es.sopragroup.core.dao;

import es.sopragroup.core.entity.UrlShortable;

public interface IUrlShortableDAO {
	
	UrlShortable saveUrl(UrlShortable url);	
	
	Long getCountUrls();

	UrlShortable getUrlByShortUrl(String shortUrl);

	UrlShortable getUrlByLongUrl(String longUrl);
}
