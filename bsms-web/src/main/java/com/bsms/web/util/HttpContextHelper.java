package com.bsms.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @ClassName: HttpContextHelper.java
 * @Description: http请求上下文工具类
 * @author: 张凯
 * @Date: 2018年7月14日 下午5:54:14
 * @parma <T>
 */
public class HttpContextHelper {

	public HttpContextHelper() {
	}

	/**
	 * 获取当前Http请求的request
	 * 
	 * @return
	 */
	public static HttpServletRequest currentRequest() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		return requestAttributes.getRequest();
	}

	/**
	 * 获取当前Http请求的respose
	 * 
	 * @return
	 */
	public static HttpServletResponse currentResponse() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		return requestAttributes.getResponse();
	}

	/**
	 * 获取当前Http请求的session
	 * 
	 * @return
	 */
	public static HttpSession currentSession() {
		return currentRequest().getSession();
	}

}
