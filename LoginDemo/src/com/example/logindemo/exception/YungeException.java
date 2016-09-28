package com.example.logindemo.exception;

/**
 * 云歌广告平台异常类
 * 
 * @author yinjy
 */
public class YungeException extends Exception {

	/** 异常编码 */
	private ExceptionCode code = ExceptionCode.UNKNOWN_ERROR;

	/**
	 * 构造函数
	 */
	public YungeException() {
		super();
	}

	/**
	 * 构造函数
	 * 
	 * @param code 异常编码
	 */
	public YungeException(ExceptionCode code) {
		super();
		this.code = code;
	}

	/**
	 * 构造函数
	 * 
	 * @param message 异常消息
	 */
	public YungeException(String message) {
		super(message);
	}

	/**
	 * 构造函数
	 * 
	 * @param code 异常编码
	 * @param message 异常消息
	 */
	public YungeException(ExceptionCode code, String message) {
		super(message);
		this.code = code;
	}

	/**
	 * 构造函数
	 * 
	 * @param cause 异常原因
	 */
	public YungeException(Throwable cause) {
		super(cause);
	}

	/**
	 * 构造函数
	 * 
	 * @param code 异常编码
	 * @param cause 异常原因
	 */
	public YungeException(ExceptionCode code, Throwable cause) {
		super(cause);
		this.code = code;
	}

	/**
	 * 构造函数
	 * 
	 * @param message 异常消息
	 * @param cause 异常原因
	 */
	public YungeException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 构造函数
	 * 
	 * @param code 异常编码
	 * @param message 异常消息
	 * @param cause 异常原因
	 */
	public YungeException(ExceptionCode code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	/**
	 * 获取异常编码
	 * 
	 * @return 异常编码
	 */
	public ExceptionCode getCode() {
		return code;
	}

}
