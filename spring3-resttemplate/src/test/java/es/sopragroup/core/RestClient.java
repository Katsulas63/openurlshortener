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

import es.sopragroup.core.dao.rest.UrlTxtClient;


public class RestClient {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java " + RestClient.class.getName() + " [web-service] [url]");
            System.exit(-1);
        }
        final String wsclient = args[0];
        final String url = args[1];
        
        if ("shortenURL".equals(wsclient)) {
        	ApplicationContext applicationContext =
                    new ClassPathXmlApplicationContext("applicationContext.xml", RestClient.class);
        	UrlTxtClient client = applicationContext.getBean("urlTxtClient", UrlTxtClient.class);
            final String shortUrl = client.shortenUrl(url);
            System.out.println("shortUrl="+shortUrl);
        } else if ("expandURL".equals(wsclient)) {
        	ApplicationContext applicationContext =
                    new ClassPathXmlApplicationContext("applicationContext.xml", RestClient.class);
        	UrlTxtClient client = applicationContext.getBean("urlTxtClient", UrlTxtClient.class);
        	final String longUrl = client.expandUrl(url);
            System.out.println("longUrl="+longUrl);
        }
        
        System.out.println("END.");
    }

}
