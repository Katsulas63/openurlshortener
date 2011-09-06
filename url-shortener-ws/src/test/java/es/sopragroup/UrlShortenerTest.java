package es.sopragroup;

import es.sopragroup.core.util.UrlUtil;

public class UrlShortenerTest {
	
	private static void genShortUrl(int value) {
		final String surl = UrlUtil.generateShortUrl(value);
		final String surlh = UrlUtil.hashMD5(surl);
		
		System.out.println("hash("+value+")="+surl);
		System.out.println("hash(hash("+value+"))="+surlh);
	}

	public static void main(String[] args) {
		for (int i=0; i<=20; ++i) {
			genShortUrl(i);
		}
	}
}
