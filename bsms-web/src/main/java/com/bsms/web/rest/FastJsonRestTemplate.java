package com.bsms.web.rest;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.client.RestTemplate;

import com.bsms.web.interceptor.WebSessionIdInterceptor;
import com.bsms.web.log.Loggers;
import com.bsms.web.util.HTTPResultUtils;
import com.bsms.web.vo.HTTPResut;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @ClassName: FastJsonRestTemplate.java
 * @Description: eureka注册服务调用
 * @author: 张凯
 * @Date: 2018年7月14日 下午5:25:01
 * @parma <T>
 */
public class FastJsonRestTemplate extends RestTemplate {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	public FastJsonRestTemplate() {
		getInterceptors().add(new WebSessionIdInterceptor());
		SimpleClientHttpRequestFactory schr = new SimpleClientHttpRequestFactory();
		schr.setConnectTimeout(5000);
		schr.setReadTimeout(10000);
		setRequestFactory(schr);
	}

	/**
	 * GET方式调用服务端获取结果
	 * 
	 * @param url
	 *            请求地址
	 * @param responseType
	 *            HTTPResut<T>中T的类型
	 * @param uriVariables
	 *            参数，占位符方式拼接到URL后
	 * @return FastJson转换后的HTTPResut<T>对象
	 */
	public <T> HTTPResut<T> getForObjectResult(String url, Class<T> responseType, Object... uriVariables) {
		String result = this.getForObject(url, String.class, uriVariables);
		return HTTPResultUtils.parseObject(result, responseType);
	}

	/**
	 * GET方式调用服务端获取结果,有默认值HTTPResut<List<T>>
	 * 
	 * @param url
	 * @param defaultResultCode
	 *            HTTPResut<T>中T的数据类型
	 * @param defaulValue
	 *            HTTPResut<T>中T的数据类型
	 * @param responseType
	 *            HTTPResut<T>中T的数据类型
	 * @param uriVariables
	 *            参数，占位符方式拼接到URL后
	 * @return FastJson转换后的HTTPResut<T>对象
	 */
	public <T> HTTPResut<List<T>> getForArrayResult(String url, HTTPResut<List<T>> defaultResult, Class<T> responseType,
			Object... uriVariables) {
		try {
			String result = this.getForObject(url, String.class, uriVariables);
			return HTTPResultUtils.parseArray(result, responseType);
		} catch (Exception e) {
			Loggers.error("调用{}失败,返回默认值{}", url, defaultResult, e);
			return defaultResult;
		}

	}

	/**
	 * GET方式调用服务端获取结果(List<T>)
	 * 
	 * @param url
	 * @param responseType
	 * @param uriVariables
	 * @return
	 */
	public <T> HTTPResut<List<T>> getForArrayResult(String url, Class<T> responseType, Object... uriVariables) {
		String result = this.getForObject(url, String.class, uriVariables);
		return HTTPResultUtils.parseArray(result, responseType);
	}

	/**
	 * GET方式调用服务端获取结果(Set<T>)
	 * 
	 * @param url
	 * @param responseType
	 * @param uriVariables
	 * @return
	 */
	public <T> HTTPResut<Set<T>> getForSetResult(String url, Class<T> responseType, Object... uriVariables) {
		String result = this.getForObject(url, String.class, uriVariables);
		return HTTPResultUtils.parseSet(result, responseType);
	}

	/**
	 * GET方式调用服务端获取结果(Map<K,V>)
	 * 
	 * @param url
	 * @param keyClazz
	 * @param valueClazz
	 * @param uriVariables
	 * @return
	 */
	public <K, V> HTTPResut<Map<K, V>> getForMapResult(String url, Class<K> keyClazz, Class<V> valueClazz,
			Object... uriVariables) {
		String result = this.getForObject(url, String.class, uriVariables);
		return HTTPResultUtils.parseMap(result, keyClazz, valueClazz);
	}

	/**
	 * POST 方式调用服务端获取结果
	 * 
	 * @param url
	 * @param r
	 * @param responseType
	 * @return
	 */
	public <R, T> HTTPResut<T> postForAnObjectResult(String url, R r, Class<T> responseType) {
		return postForAnObjectResult(url, r, responseType, true);
	}

	/**
	 * POST 方式调用服务端获取结果
	 * 
	 * @param url
	 * @param r
	 * @param responseType
	 * @param logResult
	 *            是否打印出参日志开关
	 * @return
	 */
	public <R, T> HTTPResut<T> postForAnObjectResult(String url, R r, Class<T> responseType, boolean logResult) {
		String result = exchangeResult(url, HttpMethod.POST, r, String.class, logResult);
		return HTTPResultUtils.parseObject(result, responseType);
	}

	/**
	 * POST 方式调用服务端获取结果(调用失败返回默认值,HTTPResut<T>)
	 * 
	 * @param url
	 * @param defaultResult
	 * @param r
	 * @param responseType
	 * @param logResult
	 * @return
	 */
	public <R, T> HTTPResut<T> postForAnObjectResult(String url, HTTPResut<T> defaultResult, R r, Class<T> responseType,
			boolean logResult) {
		try {
			String result = exchangeResult(url, HttpMethod.POST, r, String.class, logResult);
			return HTTPResultUtils.parseObject(result, responseType);
		} catch (Exception e) {
			Loggers.info("调用{}失败,返回默认值{}", url, defaultResult, e);
			return defaultResult;
		}
	}

	/**
	 * POST 方式调用服务端获取结果(List<T>)
	 * 
	 * @param url
	 * @param r
	 * @param responseType
	 * @return
	 */
	public <R, T> HTTPResut<List<T>> postForArrayResult(String url, R r, Class<T> responseType) {
		String result = exchangeResult(url, HttpMethod.POST, r, String.class, true);
		return HTTPResultUtils.parseArray(result, responseType);
	}

	/**
	 * PUT 方式调用服务端返回结果
	 * 
	 * @param url
	 * @param r
	 * @param responseType
	 * @return
	 */
	public <R, T> HTTPResut<T> putForObjectResult(String url, R r, Class<T> responseType) {
		String result = exchangeResult(url, HttpMethod.PUT, r, String.class, true);
		return HTTPResultUtils.parseObject(result, responseType);
	}

	/**
	 * PUT 方式调用服务端返回结果(List<T>)
	 * 
	 * @param url
	 * @param r
	 * @param responseType
	 * @return
	 */
	public <R, T> HTTPResut<List<T>> putForArrayResult(String url, R r, Class<T> responseType) {
		String result = exchangeResult(url, HttpMethod.PUT, r, String.class, true);
		return HTTPResultUtils.parseArray(result, responseType);
	}

	/**
	 * DLETE 调用服务端
	 * 
	 * @param uriTemplate
	 * @param responseType
	 * @param uriVariables
	 * @return
	 */
	public <T> HTTPResut<T> deleteForObjectResult(String uriTemplate, Class<T> responseType, Object... uriVariables) {
		URI expand = super.getUriTemplateHandler().expand(uriTemplate, uriVariables);
		String result = exchangeResult(expand.toString(), HttpMethod.DELETE, null, String.class, true);
		return HTTPResultUtils.parseObject(result, responseType);
	}

	/**
	 * DLETE 调用服务端
	 * 
	 * @param uriTemplate
	 * @param responseType
	 * @param uriVariables
	 * @return
	 */
	public <T> HTTPResut<List<T>> deleteForArrayResult(String uriTemplate, Class<T> responseType,
			Object... uriVariables) {
		URI expand = super.getUriTemplateHandler().expand(uriTemplate, uriVariables);
		String result = exchangeResult(expand.toString(), HttpMethod.DELETE, null, String.class, true);
		return HTTPResultUtils.parseArray(result, responseType);
	}

	@Override
	public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables) {
		try {
			Loggers.info("请求调用方式:{} 调用服务URL:{},uriVariables:{}", HttpMethod.GET, url,
					MAPPER.writeValueAsString(uriVariables));
		} catch (JsonProcessingException e) {
			Loggers.info("请求调用方式:{} 调用服务URL:{},请求日志参数打印失败", HttpMethod.GET, url);
		}
		T result = super.getForObject(url, responseType, uriVariables);
		try {
			Loggers.info("请求调用方式:{} 调用服务URL:{},result:{}", HttpMethod.GET, url, MAPPER.writeValueAsString(result));
		} catch (JsonProcessingException e) {
			Loggers.info("请求调用方式:{} 调用服务URL:{},输出日志结果打印失败", HttpMethod.GET, url);
		}
		return result;
	}

	/**
	 * 通用调用服务端获取结果
	 * 
	 * @param url
	 * @param method
	 *            请求方式,包括GET,POST,DELETE,PUT,PATCH等
	 * @param r
	 *            请求参数,会放到RequestBody中
	 * @param bodyType
	 *            请求结果返回值转换类型
	 * @param logResult
	 *            是否打印返回值日志开关
	 * @return FastJson转换后的HTTPResut<T>对象
	 */
	public <R, T> T exchangeResult(String url, HttpMethod method, R r, Class<T> bodyType, boolean logResult) {
		try {
			Loggers.info("请求调用方式:{} 调用服务URL:{},body:{}", method, url, MAPPER.writeValueAsString(r));
		} catch (JsonProcessingException e) {
			Loggers.info("请求调用方式:{} 调用服务URL:{},请求日志参数打印失败", method, url);
		}
		HttpHeaders headers = new HttpHeaders();
		MimeType mimeType = MimeTypeUtils.parseMimeType("application/json");
		MediaType mediaType = new MediaType(mimeType.getType(), mimeType.getSubtype(), Charset.forName("UTF-8"));
		headers.setContentType(mediaType);
		HttpEntity<R> entity = new HttpEntity<R>(r, headers);

		ResponseEntity<T> responseEntity = super.exchange(url, method, entity, bodyType);

		try {
			if (logResult) {
				Loggers.info("请求调用方式:{} 调用服务URL:{},result:{}", method, url,
						MAPPER.writeValueAsString(responseEntity.getBody()));
			} else {
				Loggers.info("请求调用方式:{} 调用服务URL:{}", method, url);
			}
		} catch (Exception e) {
			Loggers.info("请求调用方式:{} 调用服务URL:{},输出结果日志打印失败", method, url);
		}
		return responseEntity.getBody();
	}

}
