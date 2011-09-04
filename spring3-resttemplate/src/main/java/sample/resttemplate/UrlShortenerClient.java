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

package sample.resttemplate;

import org.springframework.web.client.RestOperations;
import org.springframework.xml.xpath.XPathOperations;

public class UrlShortenerClient {

    private final RestOperations restTemplate;

    private final XPathOperations xpathTemplate;
    
    private static final String SOURCE_REST_SERVER = "http://localhost:8080/url-shortener-ws/rest/";
    private static final String EXPAND_URL_REST_SERVER = SOURCE_REST_SERVER + "expandURL/{shortURL}"; 
    private static final String SHORTEN_URL_REST_SERVER = SOURCE_REST_SERVER +"shortenURL/{longURL}"; 

    public UrlShortenerClient(RestOperations restTemplate, XPathOperations xpathTemplate) {
        this.restTemplate = restTemplate;
        this.xpathTemplate = xpathTemplate;
    }

    public String expandUrl(String shortUrl) {
    	final String longUrl = restTemplate.getForObject(EXPAND_URL_REST_SERVER, String.class, shortUrl);

        return longUrl;
    }
    
    public String shortenUrl(String longUrl) {
    	final String shortUrl = restTemplate.getForObject(SHORTEN_URL_REST_SERVER, String.class, longUrl);

        return shortUrl;
    }

    

}
