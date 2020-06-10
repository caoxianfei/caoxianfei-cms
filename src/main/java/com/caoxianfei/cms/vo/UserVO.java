/**
 * 
 */
package com.caoxianfei.cms.vo;

import com.caoxianfei.cms.domain.User;

/**
 * @author (caoxianfei) email:(948315767@qq.com)
 * @version v1.0
 * @date 
 *	  创建于:2020年6月2日下午7:16:45
 */
public class UserVO extends User{

	private String createdStart;
	
	private String createdEnd;

	public String getCreatedStart() {
		return createdStart;
	}

	public void setCreatedStart(String createdStart) {
		this.createdStart = createdStart;
	}

	public String getCreatedEnd() {
		return createdEnd;
	}

	public void setCreatedEnd(String createdEnd) {
		this.createdEnd = createdEnd;
	}

	@Override
	public String toString() {
		return "UserVO [createdStart=" + createdStart + ", createdEnd=" + createdEnd + "]";
	}

	public UserVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
