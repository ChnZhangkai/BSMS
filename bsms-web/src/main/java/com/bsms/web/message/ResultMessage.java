package com.bsms.web.message;

/**
 * @ClassName: ResultMessage.java
 * @Description: 返回消息类型
 * @author: 张凯
 * @Date: 2018年7月14日 下午3:56:33
 * @parma <T>
 */
public final class ResultMessage {

	public final String code;
	public final String message;

	public ResultMessage(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public static ResultMessage newInstance(String code, String message) {
		return new ResultMessage(code, message);
	}

}
