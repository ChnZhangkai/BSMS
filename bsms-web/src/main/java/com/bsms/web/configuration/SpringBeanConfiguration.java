package com.bsms.web.configuration;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName: SpringBeanHelper.java
 * @Description: spring容器获取Bean工具
 * @author: 张凯
 * @Date: 2018年7月14日 下午5:37:35
 * @parma <T>
 */
@Component("base.springBeanHelper")
public class SpringBeanConfiguration implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	public SpringBeanConfiguration() {
	}

	public static SpringBeanConfiguration getInstance() {
		return new SpringBeanConfiguration();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (applicationContext != null) {
			SpringBeanConfiguration.applicationContext = applicationContext;
		}
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

	public static <T> T getBean(Class<T> clazz) {
		return applicationContext.getBean(clazz);
	}

	public static <T> T getBean(String beanName, Class<T> clazz) {
		return applicationContext.getBean(beanName, clazz);
	}
}
