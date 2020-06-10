/**
 * 
 */
package com.caoxianfei.cms.service.impl;

import java.util.Date;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.caoxianfei.cms.dao.UserMapper;
import com.caoxianfei.cms.domain.User;
import com.caoxianfei.cms.service.UserService;
import com.caoxianfei.cms.util.CMSException;
import com.caoxianfei.cms.util.Md5Util;
import com.caoxianfei.cms.vo.UserVO;
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

	@Autowired
	private UserMapper mapper;
	
	

	public int update(User user) {
		// TODO Auto-generated method stub
		return mapper.update(user);
	}



	public PageInfo<User> selects(UserVO userVO, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		List<User> list = mapper.selects(userVO);
		return new PageInfo<User>(list);
	}



	public int insert(User user) {
		// TODO Auto-generated method stub
		//1.用户名不可为空
		if(!StringUtil.hasText(user.getUsername()))
			throw new CMSException("用户名不可为空!");
		//2.验证用户名的长度为5-10位之间
		if(!(user.getUsername().length()>=5&&user.getUsername().length()<=10)) 
			throw new CMSException("用户名长度为5-10位");
		//3.密码不能为空
		if(!StringUtil.hasText(user.getPassword()))
			throw new CMSException("密码不可为空!");
		//4.密码在6-10位之间
		if(!(user.getPassword().length()>=5&&user.getPassword().length()<=10))
			throw new CMSException("密码在6-10位之间!");
		//5.两次密码一致
		if(!user.getPassword().equals(user.getRepassword()))
			throw new CMSException("两次密码不一致!");
		//6.用户名不能重复注册
			User u = mapper.selectByName(user.getUsername());
			if(null != u) {
				throw new CMSException("该用户名已被注册");
			}
		//7.MD5加密用户注册密码
		user.setPassword(Md5Util.md5Encode(user.getPassword()));
		user.setCreated(new Date());
		user.setUpdated(new Date());
		return mapper.insert(user);
	
	}



	public User selectByName(String name) {
		// TODO Auto-generated method stub
		return mapper.selectByName(name);
	}



	public User loginto(User user) {
		// TODO Auto-generated method stub
		//1.用户名不可为空
				if(!StringUtil.hasText(user.getUsername()))
					throw new CMSException("用户名不可为空!");
				User u = mapper.selectByName(user.getUsername());
				//2.用户是否存在
				if(null == u)
					throw new CMSException("该用户不存在");
				//3.密码不能为空
				if(!StringUtil.hasText(user.getPassword()))
					throw new CMSException("密码不可为空!");
				//密码是否与数据库加密后密码对应
				if(!(u.getPassword().equals(Md5Util.md5Encode(user.getPassword()))))
					throw new CMSException("密码不正确");
				//该用户是否为已被禁用用户
				if(u.getLocked()==1)
					throw new CMSException("账号已被禁用,请联系管理员");

		return u;
	}
	

	
}





	

