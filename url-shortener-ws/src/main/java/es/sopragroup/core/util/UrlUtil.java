package es.sopragroup.core.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
		String result = "";
		try {
			result = URLDecoder.decode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	public static String generateShortUrl(Integer longUrl) {
		return generateShortUrl(longUrl.toString());
	}

	public static String generateShortUrl(String longUrl) {
//		final StringBuilder result = new StringBuilder();
//
//		// final Long countUrls = getCountUrls();
//
//		if (longUrl.contains("http://")) {
//			result.append("http://us").append(0).append(".");
//		} else if (longUrl.contains("https://")) {
//			result.append("https://us").append(0).append(".");
//		}
//
//		result.append(longUrl).append("-recortá.");
//
//		return result.toString();
		return hashMD5(longUrl);
	}

	public static String hashMD5(String value) {
		return hash(value, "MD5");
	}

	public static String hashMD5(Integer value) {
		return hashMD5(value.toString());
	}

	public static String hashCRC32(Integer value) {
		return hashCRC32(value.toString());
	}

	public static String hashCRC32(String value) {
		return hash(value, "CRC32");
	}

	private static String hash(String value, String algorithm) {
		String result = "";
		try {

			byte[] textBytes = value.getBytes();
			MessageDigest md = MessageDigest.getInstance(algorithm);
			md.update(textBytes);
			byte[] codigo = md.digest();
			result = convertToHex(codigo);

		} catch (NoSuchAlgorithmException ex) {

		}
		return result;
	}

	/**
	 * Convierta a Hexa
	 * 
	 * @param data
	 * @return
	 */
	private static String convertToHex(byte[] data) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;
			do {
				if ((0 <= halfbyte) && (halfbyte <= 9))
					buf.append((char) ('0' + halfbyte));
				else
					buf.append((char) ('a' + (halfbyte - 10)));
				halfbyte = data[i] & 0x0F;
			} while (two_halfs++ < 1);
		}
		return buf.toString();
	}

}
