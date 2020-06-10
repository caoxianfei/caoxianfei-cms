/**
 * 
 */
package com.caoxianfei.cms.service;

import java.util.List;



import org.apache.ibatis.annotations.Param;

import com.caoxianfei.cms.domain.User;
import com.caoxianfei.cms.vo.UserVO;
import com.github.pagehelper.PageInfo;

/**
 * @author (caoxianfei) email:(948315767@qq.com)
 * @version v1.0
 * @date 
 *	  创建于:2020年4月27日下午9:04:17
 */
public interface UserService {


	/**
	 * 
	 * @Title: selects 
	 * @Description: TODO
	 * @param userVO
	 * @return
	 * @return: List<User>
	 */
	PageInfo<User> selects(UserVO userVO,Integer pageNum,Integer pageSize);
	/**
	 * 
	 * @Title: update 
	 * @Description: 修改用户
	 * @param user
	 * @return
	 * @return: int
	 */
	int update(User user);
	
	int insert(User user);
	
	User selectByName(String name);
	
	User loginto(User user);
}
