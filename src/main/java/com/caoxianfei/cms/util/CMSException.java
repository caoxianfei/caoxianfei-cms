package com.caoxianfei.cms.util;

/**   
 *    			cms异常类
 * @author (caoxianfei) email:(948315767@qq.com)
 * @version v1.0
 * @date       
 *	  创建于:2020年6月5日下午7:14:42
 */
public class CMSException extends RuntimeException {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	private String msssage;//消息

	public CMSException() {
		super();
	}
	public CMSException(String msssage) {
		super(msssage);
		this.msssage = msssage;

	}

	public String getMsssage() {
		return msssage;
	}

	public void setMsssage(String msssage) {
		this.msssage = msssage;
	}



}
