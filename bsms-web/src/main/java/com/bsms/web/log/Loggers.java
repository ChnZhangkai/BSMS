package com.bsms.web.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: Loggers.java
 * @Description: 日志通用工具
 * @author: 张凯
 * @Date: 2018年7月14日 下午1:27:09
 * @parma <T>
 */
public final class Loggers {

	private static final Logger logger = LoggerFactory.getLogger(Loggers.class);

	public static void info(String format, Object... object) {
		if (logger.isInfoEnabled()) {
			logger.info(String.format("%s %s", getStackTraceElementString(), format), object);
		}
	}

	public static void debug(String format, Object... object) {
		if (logger.isDebugEnabled()) {
			logger.info(String.format("%s %s", getStackTraceElementString(), format), object);
		}
	}

	public static void warn(String format, Object... object) {
		if (logger.isWarnEnabled()) {
			logger.info(String.format("%s %s", getStackTraceElementString(), format), object);
		}
	}

	public static void error(String format, Object... object) {
		if (logger.isErrorEnabled()) {
			logger.info(String.format("%s %s", getStackTraceElementString(), format), object);
		}
	}

	public static void info(Throwable t) {
		info("", t);
	}

	public static void debug(Throwable t) {
		debug("", t);
	}

	public static void warn(Throwable t) {
		warn("", t);
	}

	public static void error(Throwable t) {
		error("", t);
	}

	private static String getStackTraceElementString() {
		StackTraceElement stackTraceElement = new Throwable().getStackTrace()[2];
		return stackTraceElement.toString();
	}

}
