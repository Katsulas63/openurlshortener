package org.urlshortener.web.utils;

import org.urlshortener.core.service.IUrlShortenerWsManager;
import org.urlshortener.core.util.ConfigUtil;
import org.urlshortener.core.util.SpringUtil;

/**
 * Clase de utilidades de URL-Shortening 
 * 
 * @author cyague
 *
 */
public final class UrlShortenerUtil {

	private static final String DEFAULT_CLOUD_HTTP_SERVER="http://url-shortener-ws.cloudfoundry.com";
	
	/**
	 * Obtiene la instancia de Spring del manager que ofrece servicios de
	 * url-shortening mediante conexión a un WS
	 * 
	 * @return
	 */
	public static IUrlShortenerWsManager getManager() {
		return (IUrlShortenerWsManager) SpringUtil.getBean(
				"urlShortenerWsManager", IUrlShortenerWsManager.class,
				"classpath*:/spring/applicationContext-service-ws.xml");
	}

	/**
	 * Obtiene el valor de la propiedad con la ruta del servidor web en el que
	 * se encuentran los WS se url-shortening<br>
	 * . Esta propiedad se especifica en el fichero 'application.properties'
	 * 
	 * @return
	 */
	public static String getCloudHttpServer() {
		String result = ConfigUtil.getConfigProperty("CLOUD_HTTP_SERVER");
		
		if (result == null) {
			result = DEFAULT_CLOUD_HTTP_SERVER;
		}
		return result;
	}

}
