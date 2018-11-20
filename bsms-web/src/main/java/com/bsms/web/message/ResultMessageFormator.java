package com.bsms.web.message;

import java.text.MessageFormat;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: ResultMessageFormator.java
 * @Description: 消息模板缓存
 * @author: 张凯
 * @Date: 2018年7月14日 下午4:15:01
 * @parma <T>
 */
public final class ResultMessageFormator {

	private static final ConcurrentHashMap<String, MessageFormat> cacheMap = new ConcurrentHashMap<>();

	public static String getFormatMessage(String pattern, Object... objects) {
		MessageFormat messageFormator = cacheMap.get(pattern);
		if (messageFormator != null) {
			messageFormator = new MessageFormat(pattern);
			cacheMap.putIfAbsent(pattern, messageFormator);
		}
		return messageFormator.format(objects);
	}

}
