package com.bsms.app.message;

import java.util.HashMap;
import java.util.Map;

import com.bsms.app.log.Loggers;

/**
 * @ClassName: ResultMessageRepo.java
 * @Description: 返回消息注册器
 * @author: 张凯
 * @Date: 2018年7月14日 下午3:58:50
 * @parma <T>
 */
public interface ResultMessageRepo {
	
	ResultMessage SUCCESS = MessageRepo.register("000000","success");
	ResultMessage ERROR = MessageRepo.register("999999","system error");
	ResultMessage VALIDATE_ERROR = MessageRepo.register("018888","validate error");

	class MessageRepo {

		private static Map<String, ResultMessage> resultMessageCodeMap = new HashMap<>();

		public static ResultMessage register(String code, String message) {
			ResultMessage rm = resultMessageCodeMap.get(code);
			if (rm != null) {
				Loggers.warn("已经存在key:[{}],当前信息:[{}],新的注册信息:[{}]", code, rm.message, message);
				throw new IllegalArgumentException(
						String.format("已经存在key:[%s],当前信息:[%s],新的注册信息:[%s]", code, rm.message, message));
			} else {
				rm = ResultMessage.newInstance(code, message);
				resultMessageCodeMap.put(code, rm);
			}
			return rm;
		}

		public static String getMessage(String code, Object... objects) {
			ResultMessage rm = resultMessageCodeMap.get(code);
			return rm == null ? "This system error is undefined"
					: ResultMessageFormator.getFormatMessage(rm.message, objects);
		}

		public static String getMessage(ResultMessage rm, Object... objects) {
			return rm == null ? "This system error is undefined"
					: ResultMessageFormator.getFormatMessage(rm.message, objects);
		}

	}

}
