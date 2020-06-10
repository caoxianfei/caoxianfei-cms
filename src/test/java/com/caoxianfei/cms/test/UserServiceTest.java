/**
 * 
 */
package com.caoxianfei.cms.test;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.caoxianfei.cms.dao.UserMapper;
import com.caoxianfei.cms.domain.Gender;
import com.caoxianfei.cms.domain.User;
import com.caoxianfei.cms.service.ArticleService;
import com.caoxianfei.cms.service.UserService;

/**
 * @author (caoxianfei) email:(948315767@qq.com)
 * @version v1.0
 * @date 
 *	  创建于:2020年4月27日下午9:20:47
 */
 @RunWith(SpringJUnit4ClassRunner.class) 
 @ContextConfiguration(locations = "classpath:spring-beans.xml") 
public class UserServiceTest {

	
	  @Autowired 
	  private UserService userService;
	 
	  @Autowired
	  private ArticleService articleService;
	

	
	
	
}
