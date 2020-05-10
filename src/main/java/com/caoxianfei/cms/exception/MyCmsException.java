package com.caoxianfei.cms.exception;

import java.io.Serializable;

public class MyCmsException<T> implements Serializable {
	
	
	/**
	 * 版本控制
	 */
	private static final long serialVersionUID = 1L;

	private  String code;
	
	private String msg;
	
	private T data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	

	
	

}
