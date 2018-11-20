package com.bsms.web.exception;

import com.bsms.web.message.ResultMessage;
import com.bsms.web.message.ResultMessageRepo;

/**
 * @ClassName: BusinessException.java
 * @Description: 通用业务异常
 * @author: 张凯
 * @Date: 2018年7月14日 下午3:48:45
 * @parma <T>
 */
public final class BusinessException extends Exception {

	private static final long serialVersionUID = -9139850815394975733L;

	public final String code;

	public BusinessException(String code, String msg) {
		super(msg);
		this.code = code;
	}

	public static BusinessException newInstance(String code, String msg) {
		return new BusinessException(code, msg);
	}

	public static BusinessException newInstance(ResultMessage rm, Object... objects) {
		if (rm == null) {
			return defaulInstance();
		}
		return new BusinessException(rm.code, ResultMessageRepo.MessageRepo.getMessage(rm, objects));
	}

	public static BusinessException defaulInstance() {
		return newInstance(ResultMessageRepo.ERROR);
	}

}
