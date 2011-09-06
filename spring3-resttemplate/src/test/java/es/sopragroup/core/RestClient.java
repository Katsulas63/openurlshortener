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

package es.sopragroup.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.sopragroup.core.dao.rest.CustomerClient;
import es.sopragroup.core.dao.rest.FlickrClient;
import es.sopragroup.core.dao.rest.UrlTxtClient;



public class RestClient {

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java " + RestClient.class.getName() + " [Flickr API key] [search term]");
            System.exit(-1);
        }
        String apiKey = args[0];
        String searchTerm = args[1];
        String wsclient = args[2];
        
        if ("flickr".equals(wsclient)) {
        	ApplicationContext applicationContext =
                    new ClassPathXmlApplicationContext("applicationContext.xml", RestClient.class);
            FlickrClient client = applicationContext.getBean("flickrClient", FlickrClient.class);
            client.doIt(apiKey, searchTerm);
        } else if ("customer".equals(wsclient)) {
        	ApplicationContext applicationContext =
                    new ClassPathXmlApplicationContext("applicationContext.xml", RestClient.class);
            CustomerClient client = applicationContext.getBean("customerClient", CustomerClient.class);
            client.doIt();
        } else if ("shortenURL".equals(wsclient)) {
        	ApplicationContext applicationContext =
                    new ClassPathXmlApplicationContext("applicationContext.xml", RestClient.class);
        	UrlTxtClient client = applicationContext.getBean("urlTxtClient", UrlTxtClient.class);
            final String shortUrl = client.shortenUrl("http://www.yahoo.es/pop3");
            System.out.println("shortUrl="+shortUrl);
        } else if ("expandURL".equals(wsclient)) {
        	ApplicationContext applicationContext =
                    new ClassPathXmlApplicationContext("applicationContext.xml", RestClient.class);
        	UrlTxtClient client = applicationContext.getBean("urlTxtClient", UrlTxtClient.class);
        	final String longUrl = client.expandUrl("c81e728d9d4c2f636f067f89cc14862c");
            System.out.println("longUrl="+longUrl);
        }
        
        System.out.println("END.");
    }

}
