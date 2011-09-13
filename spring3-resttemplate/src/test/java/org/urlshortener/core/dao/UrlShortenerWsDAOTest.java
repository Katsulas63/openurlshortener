package org.urlshortener.core.dao;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.urlshortener.core.dao.IUrlShortenerWsDAO;

/**
 * 
 * @author cyague
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = UrlShortenerWsDAOTest.APPLICATION_CONTEXT)
@Ignore
public class UrlShortenerWsDAOTest {
	
	/**
	 * constants<br>
	 * =========
	 */
	
	public static final String APPLICATION_CONTEXT = "classpath:/spring/applicationContext-tests-ws.xml";
	
	private static final String CLOUD_HTTP_SERVER = "http://url-shortener-ws.cloudfoundry.com";
	private static final Boolean LOCAL_MODE = Boolean.FALSE;
	private static final String LONG_URL = "http://www.facebook.com/settings";
	private static final String SHORT_URL = "http://ushrt.tk/66f887";

	private static final String SHORTEN_URL_ERROR = "Error shortening Url:\n";

	private static final String EXPAND_URL_ERROR = "Error expanding Url:\n";
	private static final String EXPAND_URL_ERROR1 = "ERROR: The specified short-url cannot be expanded.";

	/**
	 * fields<br>
	 * ======
	 */
	
	@Autowired
	private IUrlShortenerWsDAO urlShortenerWsDAO;

	/**
	 * 
	 */
	@Test
	public void testService() {
		
		String shortUrl = null, longUrl = null;

		final Double randomValue = Math.random() * 1000;
		final String randomUrl = LONG_URL + "/" + randomValue.intValue();

		if (LOCAL_MODE) {
			shortUrl = urlShortenerWsDAO.shortenUrl(randomUrl);
		} else {
			shortUrl = urlShortenerWsDAO.shortenUrl(randomUrl,
					CLOUD_HTTP_SERVER);
		}

		Assert.notNull(shortUrl, SHORTEN_URL_ERROR + "shortUrl is null");

		if (LOCAL_MODE) {
			longUrl = urlShortenerWsDAO.expandUrl(shortUrl);
		} else {
			longUrl = urlShortenerWsDAO.expandUrl(shortUrl, CLOUD_HTTP_SERVER);
		}

		Assert.notNull(longUrl, EXPAND_URL_ERROR + "longUrl is null");

		Assert.isTrue(!longUrl.equalsIgnoreCase(EXPAND_URL_ERROR1),
				EXPAND_URL_ERROR + EXPAND_URL_ERROR1);

		Assert.isTrue(randomUrl.equalsIgnoreCase(longUrl),
				"Error: shortUrl and longUrl are different.");
	}

	/**
	 * 
	 */
	@Test
	public void testShortenUrlService() {
		String shortUrl = null;

		if (LOCAL_MODE) {
			shortUrl = urlShortenerWsDAO.shortenUrl(LONG_URL);
		} else {
			shortUrl = urlShortenerWsDAO
					.shortenUrl(LONG_URL, CLOUD_HTTP_SERVER);
		}

		Assert.notNull(shortUrl, SHORTEN_URL_ERROR + "shortUrl is null");
	}

	/**
	 * 
	 */
	@Test
	public void testExpandUrlService() {
		String longUrl = null;

		if (LOCAL_MODE) {
			longUrl = urlShortenerWsDAO.expandUrl(SHORT_URL);
		} else {
			longUrl = urlShortenerWsDAO.expandUrl(SHORT_URL, CLOUD_HTTP_SERVER);
		}

		Assert.notNull(longUrl, EXPAND_URL_ERROR + "longUrl is null");

		Assert.isTrue(!longUrl.equalsIgnoreCase(EXPAND_URL_ERROR1),
				EXPAND_URL_ERROR + EXPAND_URL_ERROR1);
	}
	
	/**
	 * getter & setters<br>
	 * ================
	 */

	/**
	 * @param urlShortenerWsDAO
	 *            the urlShortenerWsDAO to set
	 */
	public void setUrlShortenerWsDAO(IUrlShortenerWsDAO urlShortenerWsDAO) {
		this.urlShortenerWsDAO = urlShortenerWsDAO;
	}

}
