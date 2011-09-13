package org.urlshortener.web.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Wrapper de utilidades de Spring Framework.
 * 
 * @author cyague
 * 
 */
public final class SpringUtil {

	/**
	 * Obtiene la instancia de Spring de un bean 'beanName', de clase
	 * 'beanClass' localizado en un fichero de configuración de Spring
	 * 'appCtxLocation'
	 * 
	 * @param beanName
	 *            nombre del bean en el contexto de Spring
	 * @param beanClass
	 *            clase del bean
	 * @param appCtxLocation
	 *            ruta del fichero de configuración de Spring
	 * @return
	 */
	public static Object getBean(String beanName, Class<?> beanClass,
			String appCtxLocation) {
		Object bean = null;
		try {
			final ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
					appCtxLocation);
			bean = applicationContext.getBean(beanName, beanClass);
		} catch (Exception e) {
			bean = null;
		}
		return bean;
	}
}
