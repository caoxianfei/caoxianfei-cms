/**
 * 
 */
package com.caoxianfei.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


import com.caoxianfei.cms.domain.User;
import com.caoxianfei.cms.vo.UserVO;

/**
 * @author (caoxianfei) email:(948315767@qq.com)
 * @version v1.0
 * @date 
 *	  创建于:2020年4月27日下午8:55:30
 */

public interface UserMapper {

	/**
	 * 
	 * @Title: selects 
	 * @Description: TODO
	 * @param userVO
	 * @return
	 * @return: List<User>
	 */
	List<User> selects(UserVO userVO);
	/**
	 * 
	 * @Title: update 
	 * @Description: 修改用户
	 * @param user
	 * @return
	 * @return: int
	 */
	int update(User user);
	
	/**
	 * 
	 *	 功能:  插入对象  并且 验证数据正确性
	 *	@return :
	 *
	 */
	int insert(User user);
	/**
	 * 
	 *	 功能: 注册用户 注册前验证是否为已注册用户
	 *	@return :
	 *
	 */
	User selectByName(String name);
	

	 
}
