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

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class Driver {

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java " + Driver.class.getName() + " [Flickr API key] [search term]");
            System.exit(-1);
        }
        String apiKey = args[0];
        String searchTerm = args[1];
        String wsclient = args[2];
        
        if ("flickr".equals(wsclient)) {
        	ApplicationContext applicationContext =
                    new ClassPathXmlApplicationContext("applicationContext.xml", Driver.class);
            FlickrClient client = applicationContext.getBean("flickrClient", FlickrClient.class);
            client.doIt(apiKey, searchTerm);
        } else if ("customer".equals(wsclient)) {
        	ApplicationContext applicationContext =
                    new ClassPathXmlApplicationContext("applicationContext.xml", Driver.class);
            CustomerClient client = applicationContext.getBean("customerClient", CustomerClient.class);
            client.doIt();
        }
        
        System.out.println("END.");
    }

}
