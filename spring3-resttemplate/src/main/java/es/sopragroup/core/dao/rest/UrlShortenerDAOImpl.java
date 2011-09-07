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

package es.sopragroup.core.dao.rest;

import org.springframework.web.client.RestOperations;
import org.springframework.xml.xpath.XPathOperations;

import es.sopragroup.core.dao.IUrlShortenerDAO;
import es.sopragroup.core.util.UrlUtil;

public class UrlShortenerDAOImpl implements IUrlShortenerDAO {

	private static final String DEFAULT_SERVER = "http://localhost:8090/url-shortener-ws";
	private static final String REST_SUBCONTEXT = "/rest/";
	private static final String EXPAND_URL_REST_OP = "expandURL/{shortURL}";
	private static final String SHORTEN_URL_REST_OP = "shortenURL/{longURL}";

	protected RestOperations restTemplate;

	protected XPathOperations xpathTemplate;

	public UrlShortenerDAOImpl(RestOperations restTemplate,
			XPathOperations xpathTemplate) {
		this.restTemplate = restTemplate;
		this.xpathTemplate = xpathTemplate;
	}

	public String expandUrl(String shortUrl) {
		return expandUrl(shortUrl, DEFAULT_SERVER);
	}
	
	public String expandUrl(String shortUrl, String server) {
		String longUrl = "";
		final String restServer = getRestServer(server, EXPAND_URL_REST_OP);
		
		try {
			longUrl = restTemplate.getForObject(
					restServer, String.class,
					UrlUtil.encodeUrl(shortUrl));

		} catch (org.springframework.web.client.HttpClientErrorException e) {
			longUrl = "ERROR: " + e.getLocalizedMessage();
		}
		
		return longUrl;
	}

	public String shortenUrl(String longUrl) {
		return shortenUrl(longUrl, DEFAULT_SERVER);
	}
	
	public String shortenUrl(String longUrl, String server) {		
		String shortUrl = "";
		
		final String restServer = getRestServer(server, SHORTEN_URL_REST_OP);
		try {
			shortUrl = restTemplate.getForObject(restServer,
					String.class, UrlUtil.encodeUrl(longUrl));

		} catch (org.springframework.web.client.HttpClientErrorException e) {
			shortUrl = "ERROR: " + e.getLocalizedMessage();
		} catch (org.springframework.web.client.ResourceAccessException e) {
			shortUrl = "ERROR: " + e.getLocalizedMessage();
		}

		return shortUrl;
	}
	
	private String getRestServer(String httpServer, String restOperation) {
		final StringBuilder restServer = new StringBuilder();
		restServer.append(httpServer).append(REST_SUBCONTEXT).append(restOperation);
		return restServer.toString();
	}

}
