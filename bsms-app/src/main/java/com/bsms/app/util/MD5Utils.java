package com.bsms.app.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.bsms.app.log.Loggers;

public class MD5Utils {

	public static String getMD5(String param) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(param.getBytes("utf-8"));

			byte[] digest = md5.digest();
			StringBuffer sb = new StringBuffer();
			for (byte b : digest) {
				sb.append(Character.forDigit(b >> 4 & 0xf, 16));
				sb.append(Character.forDigit(b & 0xf, 16));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			Loggers.info("MD5加密异常");
			e.printStackTrace();
		}
		return null;
	}

}
