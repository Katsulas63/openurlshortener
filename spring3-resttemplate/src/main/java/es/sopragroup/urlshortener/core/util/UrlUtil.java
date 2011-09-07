package es.sopragroup.urlshortener.core.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public final class UrlUtil {
	
	public static String encodeUrl(String url) {
		String result = "";
		try {
			result = URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// url = url.replaceAll(":", "%3A");
		// url = url.replaceAll("/", "%2F");
		return result;
	}
	
	public static String decodeUrl(String url) {
		String result="";
		try {
			result = URLDecoder.decode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
}
