/*
 * Copyright 2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.urlshortener.core.dao.rest;

import java.util.StringTokenizer;

import org.springframework.web.client.RestOperations;
import org.springframework.xml.xpath.XPathOperations;
import org.urlshortener.core.dao.IUrlShortenerWsDAO;

/**
 * 
 * @author cyague
 *
 */
public class UrlShortenerWsDAOImpl implements IUrlShortenerWsDAO {

	/**
	 * constants<br>
	 * =========
	 */
	
	private static final String DEFAULT_SERVER = "http://localhost:8090/url-shortener-ws";
	private static final String REST_SUBCONTEXT = "/rest/";
	private static final String EXPAND_URL_REST_OP = "expandURL/{shortURL}";
	private static final String SHORTEN_URL_REST_OP = "shortenURL/{longURL}";

	/**
	 * fields<br>
	 * ======
	 */
	
	protected RestOperations restTemplate;

	protected XPathOperations xpathTemplate;
	
	/**
	 * constructors<br>
	 * ============
	 */
	
	/**
	 * 
	 * @param restTemplate
	 * @param xpathTemplate
	 */
	public UrlShortenerWsDAOImpl(RestOperations restTemplate,
			XPathOperations xpathTemplate) {
		this.restTemplate = restTemplate;
		this.xpathTemplate = xpathTemplate;
	}
	
	/**
	 * public methods<br>
	 * ==============
	 */

	/**
	 * {@inheritDoc}
	 */
	public String expandUrl(String encodeShortUrl) {
		return expandUrl(encodeShortUrl, DEFAULT_SERVER);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String expandUrl(String encodeShortUrl, String server) {
		String longUrl = "";
		final String restServer = getRestServer(server, EXPAND_URL_REST_OP);
		
		try {
			longUrl = restTemplate.getForObject(
					restServer, String.class,
					encodeShortUrl);

		} catch (org.springframework.web.client.HttpClientErrorException e) {
			longUrl = "ERROR: " + e.getLocalizedMessage();
		}
		
		longUrl = prepareUrl(longUrl);
		
		return longUrl;
	}

	/**
	 * {@inheritDoc}
	 */
	public String shortenUrl(String encodeLongUrl) {
		return shortenUrl(encodeLongUrl, DEFAULT_SERVER);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String shortenUrl(String encodeLongUrl, String server) {		
		String shortUrl = "";
		
		final String restServer = getRestServer(server, SHORTEN_URL_REST_OP);
		try {
			shortUrl = restTemplate.getForObject(restServer,
					String.class, encodeLongUrl);

		} catch (org.springframework.web.client.HttpClientErrorException e) {
			shortUrl = "ERROR: " + e.getLocalizedMessage();
		} catch (org.springframework.web.client.ResourceAccessException e) {
			shortUrl = "ERROR: " + e.getLocalizedMessage();
		}
		
		shortUrl = prepareUrl(shortUrl);

		return shortUrl;
	}
	
	/**
	 * auxiliary methods<br>
	 * =================
	 */
	
	/**
	 * 
	 * @param httpServer
	 * @param restOperation
	 * @return
	 */	
	private String getRestServer(String httpServer, String restOperation) {
		final StringBuilder restServer = new StringBuilder();
		restServer.append(httpServer).append(REST_SUBCONTEXT).append(restOperation);
		return restServer.toString();
	}
	
	/**
	 * 
	 * @param url
	 * @return
	 */
	private String prepareUrl(String url) {
		String result = url;
		
		final StringTokenizer st = new StringTokenizer(url, "\n\r");
		
		if (st.countTokens() == 1) {
			result = st.nextToken();
		}
		
		return result;
	}

}
