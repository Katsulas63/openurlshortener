package org.urlshortener.core.manager;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.urlshortener.core.service.IUrlShortableManager;

/**
 * 
 * @author cyague
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = UrlShortableManagerTest.APPLICATION_CONTEXT)
@Ignore
public class UrlShortableManagerTest {
	
	/**
	 * constants<br>
	 * =========
	 */
	
	public static final String APPLICATION_CONTEXT = "classpath:/spring/applicationContext-tests.xml";
	
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
	private IUrlShortableManager urlShortableManager;

	/**
	 * 
	 */
	@Test
	public void testService() {
		
		String shortUrl = null, longUrl = null;

		final Double randomValue = Math.random() * 1000;
		final String randomUrl = LONG_URL + "/" + randomValue.intValue();

		shortUrl = urlShortableManager.shortenURL(longUrl);
	
		Assert.notNull(shortUrl, SHORTEN_URL_ERROR + "shortUrl is null");

		longUrl = urlShortableManager.expandURL(shortUrl);

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
		
		shortUrl = urlShortableManager.shortenURL(LONG_URL);

		Assert.notNull(shortUrl, SHORTEN_URL_ERROR + "shortUrl is null");
	}

	/**
	 * 
	 */
	@Test
	public void testExpandUrlService() {
		String longUrl = null;
		
		longUrl = urlShortableManager.expandURL(SHORT_URL);

		Assert.notNull(longUrl, EXPAND_URL_ERROR + "longUrl is null");

		Assert.isTrue(!longUrl.equalsIgnoreCase(EXPAND_URL_ERROR1),
				EXPAND_URL_ERROR + EXPAND_URL_ERROR1);
	}
	
	/**
	 * getter & setters<br>
	 * ================
	 */

	/**
	 * @param urlShortableManager
	 *            the urlShortableManager to set
	 */
	public void setUrlShortableManager(IUrlShortableManager urlShortableManager) {
		this.urlShortableManager = urlShortableManager;
	}

}
