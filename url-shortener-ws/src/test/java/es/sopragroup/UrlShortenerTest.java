package es.sopragroup;

import es.sopragroup.urlshortener.core.util.UrlUtils;

public class UrlShortenerTest {

	public static void main(String[] args) {
		for (int i=0; i<=20; ++i) {
			final String shortUrl = UrlUtils.generateShortUrl(i);			
			System.out.println(shortUrl);			
		}
	}
}
