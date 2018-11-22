package com.bsms.app.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: DateUtils.java
 * @Description: 日期工具类
 * @author: 张凯
 * @Date: 2018年11月22日 上午9:56:47
 * @parma <T>
 */
public final class DateUtils {
	
	private final static String yyyyMMdd = "yyyyMMdd";
	
	private final static DateFormat df = new SimpleDateFormat(yyyyMMdd);
	
	public static String dateFormartToYYYYMMDD(Date date){
		
		if (date != null) {
			return df.format(date);
		}
		
		return null;
	}
}
