package com.caoxianfei.cms.exception;

public class CmsException extends RuntimeException {
	
	/**
	 * 版本号控制
	 */
	private static final long serialVersionUID = 1L;
	
	//msg存放自己定义的错误信息
	private String  msg;

	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public CmsException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	
	
	

}
