package com.caoxianfei.cms.controller;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caoxianfei.cms.domain.User;
import com.caoxianfei.cms.exception.CmsException;
import com.caoxianfei.cms.exception.MyCmsException;
import com.caoxianfei.cms.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
  
	  @Autowired
	  private UserService userService;
	 
	  /**
	   * 
	   *   说明：更跳转登陆页面
	   * @author  caoxianfei
	   * @version v1.0
	   *  创建于：2020年5月8日
	   */
	  @RequestMapping("/to/login.do")
	  public String toLogin() {
		  return "user/login";
	  }
	  /**
	   *   说明：更跳转注册页面
	   * @author  caoxianfei
	   * @version v1.0
	   *  创建于：2020年5月8日
	   */
	  @RequestMapping("/to/reg.do")
	  public String toReg() {
		  return "user/reg";
	  }
	  
	  
	  /**
	   * 
	   *   说明：注册用户
	   * @author  caoxianfei
	   * @version v1.0
	   *  创建于：2020年5月8日
	   */
	  @RequestMapping("reg.do")
	  @ResponseBody
	  public MyCmsException<User>  reg(User u,MyCmsException<User> reCmsException) {
		  try {
			  userService.reg(u);
			  reCmsException.setCode("200");  //自定义的状态码
			  reCmsException.setMsg("注册成功！"); //自定义的信息
		} catch (CmsException e) {
			reCmsException.setCode("300");
			e.printStackTrace();
			reCmsException.setMsg(e.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
			reCmsException.setCode("405");
			reCmsException.setMsg("系统出现错误，请联系管理员！");
		}
		  return  reCmsException;
	  }
	  
	  
	  @RequestMapping("login.do")
	  @ResponseBody
	  public Boolean login(User u,HttpSession session) {
		  try {
			  User user = userService.login(u);
			  if(user !=null) {
				  session.setAttribute("loginu", user);  //用于拦截器，页面处理
				  return true;
			  }
			  return false;
		} catch (Exception e) {
			  return false;
		}
	  }

	  /**
	   * 
	   *   说明：注销
	   * @author  caoxianfei
	   * @version v1.0
	   *  创建于：2020年5月9日
	   */
	  @RequestMapping("/loginout.do")
	  public String loginout(HttpSession session) {
		  session.invalidate();
		  return "redirect:/index.do";
	  }
}
