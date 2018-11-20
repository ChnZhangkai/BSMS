package com.bsms.web.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bsms.web.aspect.CiticLogAspect;
import com.bsms.web.rest.FastJsonRestTemplate;

/**
 * @ClassName: BeansConfiguration.java
 * @Description: 初始化定义配置类
 * @author: 张凯
 * @Date: 2018年7月14日 下午5:45:45
 * @parma <T>
 */
@Configuration
public class BeansConfiguration {
	
	@Bean
	public CiticLogAspect citiLog(){
		return new CiticLogAspect();
	}

	@Bean
	@LoadBalanced
	public FastJsonRestTemplate rest() {
		return new FastJsonRestTemplate();
	}

}
