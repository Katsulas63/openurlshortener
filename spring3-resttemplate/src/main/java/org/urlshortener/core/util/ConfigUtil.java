package org.urlshortener.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
	public static String getConfigTestProperty(String propertyName) {
		return getConfigProperty(propertyName, "tests.properties");
	}
	
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
		
		Properties dbProps = new Properties();

        //The forward slash "/" in front of in_filename will ensure that
        //no package names are prepended to the filename when the Classloader
        //search for the file in the classpath
        InputStream is = ConfigUtil.class.getResourceAsStream("/"+propertiesFile);
        
        if (is != null) {
        	try {
                dbProps.load(is);
                result = dbProps.getProperty(propertyName);
            }
            catch (IOException ioe)
            {
            	result = null;
            }
        }
        
		return result;
	}
	
	/**
	 * 
	 * @return
	 */
	public static String getTestConfigProperty(String prefixProperty, String propertyName, String defaultValue) {
		String result = ConfigUtil.getConfigTestProperty(prefixProperty+propertyName);
		
		if (result == null) {
			result = defaultValue;
		}
		return result;
	}
	
	/**
	 * 
	 * @return
	 */
	public static Boolean getTestConfigBoolProperty(String prefixProperty, String propertyName, Boolean defaultValue) {
		Boolean result = null;
		final String resultStr = ConfigUtil.getConfigTestProperty(prefixProperty+propertyName);
		
		if (resultStr != null) {
			result = Boolean.parseBoolean(resultStr);
		} else {
			result = defaultValue;
		}
		return result;
	}
}
