package com.hoperun.rdc.exceptions;

import com.hoperun.rdc.exceptions.code.ExceptionCodeEnum;

/**
 * 
 * 
* @ClassName: FileException.java 
* @Description: 
* @author YinChang-bao
* @date Nov 16, 2015
*
 */
public class FileException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8849011626903118289L;
	private String errorCode = null;

	public FileException(String errorMsg) {
		super(errorMsg);
		this.errorCode = "";
	}

	public FileException(String errorCode, String errorMsg) {
		super(errorMsg);
		this.errorCode = errorCode;
	}

	public FileException(ExceptionCodeEnum exp) {
		super(exp.getExpMsg());
		this.errorCode = exp.getExpCode();
	}
	
	public FileException(ExceptionCodeEnum exp,Throwable throwable) {
		super(exp.getExpMsg(),throwable);
		this.errorCode = exp.getExpCode();
	}


	public String getErrorCode() {
		return errorCode;
	}

	@Override
	public String getLocalizedMessage() {
		return getMessage();
	}
}
