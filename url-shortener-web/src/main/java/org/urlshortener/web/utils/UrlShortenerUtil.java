package org.urlshortener.web.utils;

import org.urlshortener.core.service.IUrlShortenerWsManager;

public class UrlShortenerUtil {
	
	public static final String CLOUD_HTTP_SERVER = "http://url-shortener-ws.cloudfoundry.com";

	public static IUrlShortenerWsManager getManager() {
		return (IUrlShortenerWsManager) SpringUtil.getBean("urlShortenerWsManager",
				IUrlShortenerWsManager.class,
				"classpath*:/spring/applicationContext-service-ws.xml");
	}
}
