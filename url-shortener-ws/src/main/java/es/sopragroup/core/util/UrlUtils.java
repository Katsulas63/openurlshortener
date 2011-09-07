package es.sopragroup.core.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.StringTokenizer;

public final class UrlUtils {
	
	public static final String URL_SHORTENING_SERVER = "http://ushrt.tk/";

	/**
	 * Encodes an url to do a request call param
	 * @param url
	 * @return
	 */
	public static String encodeUrl(String url) {
		String result = "";
		
		try {
			result = URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("encodeUrl ERROR:" + e.getMessage());
		}

		return result;
	}

	/**
	 * Decodes an url from a request call param
	 * @param url
	 * @return
	 */
	public static String decodeUrl(String url) {
		String result = "";
		
		try {
			result = URLDecoder.decode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("decodeUrl ERROR:" + e.getMessage());
		}

		return result;
	}

	/**
	 * Generates a short url
	 * @param idUrl
	 * @return
	 */
	public static String generateShortUrl(Integer idUrl) {
		final StringBuilder result = new StringBuilder();
		result.append(URL_SHORTENING_SERVER);
		final String hash = CriptoUtils.hash(idUrl, "MD5");		
		final String compressedHash = CriptoUtils.compress(hash);
		
		final StringTokenizer st = new StringTokenizer(compressedHash, "@");
		if (st.countTokens() == 2) {
			st.nextToken();
			result.append(st.nextToken());
		}
				
		return result.toString();
	}

}
