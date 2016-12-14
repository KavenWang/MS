package com.uisftech.cloan.preloan.common;

/**
 * ClassName: BizCode <br/>
 * Function: 系统错误码. <br/>
 * date: 2016年10月28日 下午1:22:01 <br/>
 *
 * @author wang_kai3
 * @version
 */
public enum CommonCode {

	SUCCESS("0", "成功"),
	FAILED("-1", "失败"),;

	/**
	 * 错误码 <br>
	 * AABBCCC<br>
	 * AA系统码，BB模块编码，CCC模块内编码
	 */
	String code;

	/** 错误描述 */
	String message;

	CommonCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

}
