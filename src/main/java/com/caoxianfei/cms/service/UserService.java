/**
 * 
 */
package com.caoxianfei.cms.service;

import java.util.List;


import org.apache.ibatis.annotations.Param;

import com.caoxianfei.cms.domain.User;
import com.github.pagehelper.PageInfo;

/**
 * @author (caoxianfei) email:(948315767@qq.com)
 * @version v1.0
 * @date 
 *	  创建于:2020年4月27日下午9:04:17
 */
public interface UserService {

	int addUser(User u);
	
	
	 
	 User login(User u);



	PageInfo<User> getUserList(User user, Integer pageNum);

	Boolean updateLocked(User user);

	Boolean reg(User u);
	
	int getCountByUsername(String username);
}
