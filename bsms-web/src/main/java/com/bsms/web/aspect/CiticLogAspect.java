package com.bsms.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;

import com.bsms.web.log.Loggers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Order(1)
public class CiticLogAspect {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Pointcut("within(@com.bsms.web.annotation.CiticLog *)")
	public void beanAnnotationWithCiticLog() {
		throw new UnsupportedOperationException();
	}

	@Pointcut("execution(public * *(..))")
	public void publicMethod() {
		throw new UnsupportedOperationException();
	}

	@Pointcut("beanAnnotationWithCiticLog() && publicMethod() && !@annotation(com.bsms.web.annotation.CiticLogExcludeThis)")
	public void publicMethodInsideAClassMarkedWithCiticLog() {
		throw new UnsupportedOperationException();
	}

	@Around(value = "publicMethodInsideAClassMarkedWithCiticLog()")
	public Object aroundMethod(ProceedingJoinPoint point) throws Throwable {
		return monitor(point);
	}

	private Object monitor(ProceedingJoinPoint point) throws Throwable {
		long startTime = System.currentTimeMillis();

		MethodSignature signature = (MethodSignature) point.getSignature();
		String fullMethodName = point.getTarget().getClass().getName() + "." + signature.getName();

		this.logInArgs(fullMethodName, point.getArgs());

		Object returnValue = null;

		try {
			returnValue = point.proceed();
			return returnValue;
		} finally {
			this.logOutArgs(fullMethodName, returnValue, System.currentTimeMillis() - startTime);
		}
	}

	private void logInArgs(String methodName, Object[] args) {
		String writeValueAsString = "";
		try {
			writeValueAsString = MAPPER.writeValueAsString(args);
		} catch (JsonProcessingException e) {
			Loggers.error(e);
		}
		Loggers.info("Method Begin({}):Args {}", methodName, writeValueAsString);
	}

	private void logOutArgs(String methodName, Object returnValue, Long time) {
		String writeValueAsString = "";
		try {
			writeValueAsString = MAPPER.writeValueAsString(returnValue);
		} catch (JsonProcessingException e) {
			Loggers.error(e);
		}
		Loggers.info("Method End({}): , cost time: {} , Return Details:[{}]", methodName, time, writeValueAsString);
	}
}
