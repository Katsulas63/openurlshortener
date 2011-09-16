package org.urlshortener.web.utils;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Wrapper de utilidades de configuración que usa Apache Commons Configuration
 * 
 * @author cyague
 *
 */
public final class ConfigUtil {

	
	
	/**
	 * Obtiene el valor de una propiedad de un fichero de configuracion, que por defecto es 'application.properties'
	 * 
	 * @param propertyName
	 * @return
	 */
	public static String getConfigProperty(String propertyName) {
		return getConfigProperty(propertyName, "application.properties");
	}
	
	/**
	 * Obtiene el valor de una propiedad de un fichero de configuracion
	 * 
	 * @param propertyName
	 * @param 
	 * @return
	 */
	public static String getConfigProperty(String propertyName, String propertiesFile) {
		String result = null;
		
		try {
			Configuration config = new PropertiesConfiguration(propertiesFile);
			result = config.getString(propertyName);
		} catch (ConfigurationException e) {
			result = null;
		}
		
		return result;
	}
}
