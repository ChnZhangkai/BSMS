package com.bsms.app.util;

import com.bsms.app.exception.BusinessException;
import com.bsms.app.message.ResultMessageRepo;

/**
 * @ClassName: CheckResultUtils.java
 * @Description: 服务调用返回对象判断工具类
 * @author: 张凯
 * @Date: 2018年7月18日 下午10:04:42
 * @parma <T>
 */
public final class CheckResultUtils {
	
	public static void checkResult(Object object) throws BusinessException{
		if (object == null) {
			throw BusinessException.newInstance(ResultMessageRepo.ERROR, "system error");
		}
	}
	
}
