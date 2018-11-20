package com.bsms.app.rest;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.bsms.app.log.Loggers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @ClassName: RestHelper.java
 * @Description: webService服务调用
 * @author: 张凯
 * @Date: 2018年7月16日 下午5:04:36
 * @parma <T>
 */
public class RestHelper {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	private RestTemplate restTemplate;

	public RestHelper(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public <T> T postRestRequest(String serviceUrl, Object request, Class<T> responseType) {

		if (StringUtils.isBlank(serviceUrl) || request == null) {
			Loggers.error("serviceUrl or request data must not be null");
		}

		Loggers.info("request url: {},params: {}, result type: {}", serviceUrl, JSONObject.toJSON(request),
				responseType);

		long begin = System.currentTimeMillis();

		T resultObject = null;

		try {
			resultObject = restTemplate.postForObject(serviceUrl, request, responseType);
			Loggers.info("rest response : {}," + resultObject);
		} catch (Exception e) {
			Loggers.error("rest request exception", e);
			throw e;
		}

		long end = System.currentTimeMillis();
		Loggers.info("get rest response, cost : {}ms", (end - begin));

		return resultObject;
	}

	public <T> T getRestRequest(String serviceUrl, Class<T> responseType) {

		if (StringUtils.isBlank(serviceUrl)) {
			Loggers.error("serviceUrl must not be null");
		}

		Loggers.info("request url: {}, response type: {}", serviceUrl, responseType);

		long begin = System.currentTimeMillis();

		T resultObject = null;

		try {
			resultObject = restTemplate.getForObject(serviceUrl, responseType);
			Loggers.info("rest response : {}," + resultObject);
		} catch (Exception e) {
			Loggers.error("rest request exception", e);
			throw e;
		}

		long end = System.currentTimeMillis();
		Loggers.info("get rest response, cost : {}ms", (end - begin));

		return resultObject;
	}

	public String getForString(String url, Object... uriVariables) {
		try {
			Loggers.info("请求调用方式:{} 调用服务URL:{},uriVariables:{}", HttpMethod.GET, url,
					MAPPER.writeValueAsString(uriVariables));
		} catch (JsonProcessingException e) {
			Loggers.info("请求调用方式:{} 调用服务URL:{},请求日志参数打印失败", HttpMethod.GET, url);
		}
		String result = restTemplate.getForObject(url, String.class, uriVariables);
		try {
			Loggers.info("请求调用方式:{} 调用服务URL:{},result:{}", HttpMethod.GET, url, MAPPER.writeValueAsString(result));
		} catch (JsonProcessingException e) {
			Loggers.info("请求调用方式:{} 调用服务URL:{},输出日志结果打印失败", HttpMethod.GET, url);
		}
		return result;
	}

	public <T> T getRestRequet(String url, Class<T> responseType, JSONObject params) {
		if (params == null || params.size() <= 0) {
			return getRestRequest(url, responseType);
		}
		if (StringUtils.isBlank(url)) {
			Loggers.error("service url must not be null");
			return null;
		}
		Loggers.info("request url: {}, params: {}, response type: {}", url, params, responseType);

		StringBuilder sb = new StringBuilder(url).append("?");
		for (Map.Entry<String, Object> param : params.entrySet()) {
			sb.append(param.getKey()).append("=").append(param.getValue()).append("&");
		}
		String serviceUrl = sb.deleteCharAt(sb.length() - 1).toString();
		long begin = System.currentTimeMillis();

		T resultObject = null;

		try {
			resultObject = restTemplate.getForObject(serviceUrl, responseType);
			Loggers.info("rest response : {}," + resultObject);
		} catch (Exception e) {
			Loggers.error("rest request exception", e);
			throw e;
		}

		long end = System.currentTimeMillis();
		Loggers.info("get rest response, cost : {}ms", (end - begin));

		return resultObject;

	}

}
