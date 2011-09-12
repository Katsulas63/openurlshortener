package org.urlshortener.web.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class SpringUtil {

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
