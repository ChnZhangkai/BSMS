package com.bsms.app.handle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bsms.app.exception.BusinessException;
import com.bsms.app.log.Loggers;
import com.bsms.app.vo.HTTPResut;

/**
 * @ClassName: GlobalExceptionHandler.java
 * @Description: 统一异常处理器
 * @author: 张凯
 * @Date: 2018年7月12日 下午11:56:25
 * @parma <T>
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 普通业务异常处理器
	 * 
	 * @param request
	 * @param response
	 * @param baseException
	 * @return
	 */
	@ExceptionHandler(BusinessException.class)
	public @ResponseBody <T> String baseException(HttpServletRequest request, HttpServletResponse response,
			BusinessException businessException) throws Throwable {
		Loggers.warn("{},返回码:{}", businessException.getMessage(),
				businessException.code == null ? "999999" : businessException.code);
		response.setContentType("application/json;charset=UTF-8");
		try {
			response.getOutputStream().write(JSON.toJSONString(HTTPResut.error(businessException)).getBytes("UTF-8"));
			response.getOutputStream().flush();
		} catch (IllegalStateException e) {
			Loggers.warn(e.getCause());
			response.getWriter().write(JSON.toJSONString(HTTPResut.error()));
			response.getWriter().flush();
		}
		// 中断springMVC流程
		return null;

	}

	/**
	 * 未定义及未补获异常处理器
	 * 
	 * @param request
	 * @param response
	 * @param throwable
	 * @return
	 */
	@ExceptionHandler(Throwable.class)
	public Object exceptionHandler(HttpServletRequest request, HttpServletResponse response, Throwable throwable)
			throws Throwable {
		Loggers.error("统一打印未捕获异常", throwable);
		response.setContentType("application/json;charset=UTF-8");
		try {
			response.getOutputStream().write(JSON.toJSONString(HTTPResut.error()).getBytes("UTF-8"));
			response.getOutputStream().flush();
		} catch (IllegalStateException e) {
			Loggers.warn(e.getCause());
			response.getWriter().write(JSON.toJSONString(HTTPResut.error()));
			response.getWriter().flush();
		}
		// 中断springMVC流程
		return null;

	}

}
