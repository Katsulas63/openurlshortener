package org.urlshortener.core.dao;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.urlshortener.core.dao.IUrlShortenerWsDAO;
import org.urlshortener.core.util.ConfigUtil;

/**
 * Test de 'urlShortenerWsDAO'
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
	
	private static final String PREFIX_PROPERTY = "DAO.TEST.";
	private static final String CLOUD_HTTP_SERVER = "CLOUD_HTTP_SERVER";
	private static final String LOCAL_MODE = "LOCAL_MODE";
	private static final String LONG_URL = "LONG_URL";
	private static final String SHORT_URL = "SHORT_URL";
	
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
		final String longUrlTest = ConfigUtil.getTestConfigProperty(PREFIX_PROPERTY, LONG_URL, null);
		final String server =  ConfigUtil.getTestConfigProperty(PREFIX_PROPERTY, CLOUD_HTTP_SERVER, null);
		final Boolean localMode =  ConfigUtil.getTestConfigBoolProperty(PREFIX_PROPERTY, LOCAL_MODE,Boolean.FALSE);

		final Double randomValue = Math.random() * 1000;
		final String randomUrl = longUrlTest + "/" + randomValue.intValue();
		

		if (localMode) {
			shortUrl = urlShortenerWsDAO.shortenUrl(randomUrl);
		} else {
			shortUrl = urlShortenerWsDAO.shortenUrl(randomUrl, server);
		}

		Assert.notNull(shortUrl, SHORTEN_URL_ERROR + "shortUrl is null");

		if (localMode) {
			longUrl = urlShortenerWsDAO.expandUrl(shortUrl);
		} else {
			longUrl = urlShortenerWsDAO.expandUrl(shortUrl, server);
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
		final Boolean localMode =  ConfigUtil.getTestConfigBoolProperty(PREFIX_PROPERTY, LOCAL_MODE,Boolean.FALSE);
		final String longUrl =  ConfigUtil.getTestConfigProperty(PREFIX_PROPERTY, LONG_URL, null);
		final String server =  ConfigUtil.getTestConfigProperty(PREFIX_PROPERTY, CLOUD_HTTP_SERVER, null);
		
		if (localMode) {
			shortUrl = urlShortenerWsDAO.shortenUrl(longUrl);
		} else {
			shortUrl = urlShortenerWsDAO
					.shortenUrl(longUrl, server);
		}

		Assert.notNull(shortUrl, SHORTEN_URL_ERROR + "shortUrl is null");
	}

	/**
	 * 
	 */
	@Test
	public void testExpandUrlService() {
		String longUrl = null;
		final Boolean localMode = ConfigUtil.getTestConfigBoolProperty(PREFIX_PROPERTY, LOCAL_MODE,Boolean.FALSE);
		final String shortUrl = ConfigUtil.getTestConfigProperty(PREFIX_PROPERTY, SHORT_URL, null);
		final String server = ConfigUtil.getTestConfigProperty(PREFIX_PROPERTY, CLOUD_HTTP_SERVER, null);
		
		if (localMode) {
			longUrl = urlShortenerWsDAO.expandUrl(shortUrl);
		} else {
			longUrl = urlShortenerWsDAO.expandUrl(shortUrl, server);
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
