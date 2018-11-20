package com.bsms.app.vo;

import java.io.Serializable;

import com.bsms.app.exception.BusinessException;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: HTTPResut.java
 * @Description: 统一返回数据封装类型
 * @author: 张凯
 * @Date: 2018年7月14日 下午3:35:00
 * @parma <T>
 */
@ApiModel(value = "统一返回数据封装类型")
public final class HTTPResut<T> implements Serializable {

	private static final long serialVersionUID = -6033908735418369683L;

	public static final String SUCCESS_CODE = "000000";
	public static final String FAILURE_CODE = "999999";
	public static final String APP_SERVICE_ERROR = "111111";
	public static final String PARAMS_VALIDATE_ERROR = "888888";

	@ApiModelProperty(value = "响应编码")
	private String resultCode;

	@ApiModelProperty(value = "响应描述")
	private String description;

	@ApiModelProperty(value = "响应数据")
	private T data;

	public HTTPResut() {

	}

	public HTTPResut(String resultCode, String description, T data) {
		this.resultCode = resultCode;
		this.description = description;
		this.data = data;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static <R> HTTPResut<R> success() {
		return success(null);
	}

	public static <R> HTTPResut<R> success(R r) {
		return new HTTPResut<>(SUCCESS_CODE, "success", r);
	}

	public static <R> HTTPResut<R> success(R r, String description) {
		return new HTTPResut<>(SUCCESS_CODE, description, r);
	}

	public static <R> HTTPResut<R> error() {
		return new HTTPResut<>(FAILURE_CODE, "system error", null);
	}

	public static <R> HTTPResut<R> errorPage() {
		return new HTTPResut<>(FAILURE_CODE, "page error", null);
	}

	public static <R> HTTPResut<R> errorParam(R r) {
		return new HTTPResut<>(PARAMS_VALIDATE_ERROR, "param error", r);
	}

	public static <R> HTTPResut<R> error(BusinessException e) {
		return new HTTPResut<>(e.code, e.getMessage(), null);
	}

}
