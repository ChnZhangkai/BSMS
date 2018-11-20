package com.bsms.app.configuration;

import java.util.Properties;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.bsms.app.aspect.CiticLogAspect;
import com.bsms.app.rest.RestHelper;
import com.github.pagehelper.PageHelper;

/**
 * @ClassName: BeansConfiguration.java
 * @Description: 初始化定义配置类
 * @author: 张凯
 * @Date: 2018年7月18日 上午12:31:27
 * @parma <T>
 */
@Configuration
public class BeansConfiguration {
	
	@Bean
	public CiticLogAspect citiLog(){
		return new CiticLogAspect();
	}
	
	@Bean
	public RestHelper rest(){
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setReadTimeout(5000);
		requestFactory.setConnectTimeout(10000);
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		return new RestHelper(restTemplate);
	}
	
	@Bean
	public MultipartConfigElement multipartConfigElement(){
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("10240KB");
		factory.setMaxRequestSize("102400KB");
		return factory.createMultipartConfig();
	}
	
	@Bean
	public PageHelper pageHelper(){
		PageHelper pageHelper = new PageHelper();
		Properties p = new Properties();
		p.setProperty("offsetAsPageNum", "true");
		p.setProperty("rowBoundsWithCount", "true");
	    p.setProperty("reasonable", "true");
	    p.setProperty("dialect", "mysql");
	    pageHelper.setProperties(p);
	    return pageHelper;
	}
	
}
