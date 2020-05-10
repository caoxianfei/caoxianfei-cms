/**
 * 
 */
package com.caoxianfei.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


import com.caoxianfei.cms.domain.User;

/**
 * @author (caoxianfei) email:(948315767@qq.com)
 * @version v1.0
 * @date 
 *	  创建于:2020年4月27日下午8:55:30
 */

public interface UserMapper {


	 int addUser(User u);
	 
	 List<User> getUserList(User user);
	 
	 User login(User u);

	 Boolean updateLocked(User user);
	 
	 Boolean reg(User u);

	 int getCountByUsername(String username);
}
