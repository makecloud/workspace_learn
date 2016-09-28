package com.example.logindemo.exception;

/**
 * 接口异常类
 * 
 * @author yinjy
 */
public class ApiException extends YungeException {

	/** 错误编码 */
	private int code = 0;

	/**
	 * 构造函数
	 * 
	 * @param code 错误编码
	 */
	public ApiException(int code) {
		super();
		this.code = code;
	}

	/**
	 * 构造函数
	 * 
	 * @param code 错误编码
	 * @param message 异常消息
	 */
	public ApiException(int code, String message) {
		super(message);
		this.code = code;
	}

	/**
	 * 构造函数
	 * 
	 * @param code 错误编码
	 * @param cause 异常原因
	 */
	public ApiException(int code, Throwable cause) {
		super(cause);
		this.code = code;
	}

	/**
	 * 构造函数
	 * 
	 * @param code 错误编码
	 * @param message 异常消息
	 * @param cause 异常原因
	 */
	public ApiException(int code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	/**
	 * 获取错误编码
	 * 
	 * @return 错误编码
	 */
	// public int getCode() {
	// return code;
	// }

}
