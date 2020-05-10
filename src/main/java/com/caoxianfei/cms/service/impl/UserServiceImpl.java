/**
 * 
 */
package com.caoxianfei.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.caoxianfei.cms.dao.UserMapper;
import com.caoxianfei.cms.domain.User;
import com.caoxianfei.cms.exception.CmsException;
import com.caoxianfei.cms.service.UserService;
import com.caoxianfei.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author (caoxianfei) email:(948315767@qq.com)
 * @version v1.0
 * @date 
 *	  创建于:2020年4月27日下午9:04:59
 */
@Service
public class UserServiceImpl implements UserService{

	private final static String salt="cxf";
	
	
	@Autowired
	private UserMapper userMapper;


	public int addUser(User u) {
		return userMapper.addUser(u);
	}


	public User login(User u) {
		//MD5加密
		String newPwd = DigestUtils.md5DigestAsHex((u.getPassword()+salt).getBytes());
		u.setPassword(newPwd);
		return userMapper.login(u);
	}


	public PageInfo<User> getUserList(User user, Integer pageNum) {
		PageHelper.startPage(pageNum, 3);
		List<User> userList = userMapper.getUserList(user);
		PageInfo<User> info = new PageInfo<User>(userList);
		return info;
	}
	
	public Boolean updateLocked(User user) {
		// TODO Auto-generated method stub
		return userMapper.updateLocked(user);
	}


	public Boolean reg(User u) {
		//判断用户名是否为空
		 if(!StringUtil.hasText(u.getUsername())) {
			 throw new CmsException("用户名不能为空");
		 }
		 //判断用户名的长度
		 if(!(u.getUsername().length()>=3 && u.getUsername().length()<=6)) {
			 throw new CmsException("用户名长度3-6位");
		 }
		 //判断用户名是否存在
		 if(getCountByUsername(u.getUsername())>0) {
			 throw new CmsException("用户名称已经存在");
		 }
		//判断密码是否为空
		 if(!StringUtil.hasText(u.getPassword())) {
			 throw new CmsException("密码不能为空");
		 }
		 //判断用户名的长度
		 if(!(u.getPassword().length()>=3 && u.getPassword().length()<=6)) {
			 throw new CmsException("密码长度3-6位");
		 }
		 
		//MD5加密
		String newPwd = DigestUtils.md5DigestAsHex((u.getPassword()+salt).getBytes());
		u.setPassword(newPwd);
		
		return userMapper.reg(u);
	}


	public int getCountByUsername(String username) {
		// TODO Auto-generated method stub
		return userMapper.getCountByUsername(username);
	}
	
	
	
	
}
