package com.uisftech.cloan.preloan.common;
/**
 * 返回信息
 * @author wangwei
 *
 */
public class ResponseData {

	private String message;
	private String code;
	private Object data;

	public ResponseData() {
		result(CommonCode.SUCCESS);
	}
	public ResponseData(Object data) {
		result(CommonCode.SUCCESS);
		this.data = data;
	}

	public ResponseData(String code, String message, Object data) {
		this.code = code;
		this.message = message;
		if (data == null) {
			data = "";
		}
		this.data = data;
	}

	public void result(CommonCode commonCode) {
		if (commonCode != null) {
			this.code = commonCode.getCode();
			this.message = commonCode.getMessage();
		}
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}



}
