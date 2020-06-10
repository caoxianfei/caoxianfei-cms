/**
 * 
 */
package com.caoxianfei.cms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caoxianfei.cms.domain.User;
import com.caoxianfei.cms.service.UserService;
import com.caoxianfei.cms.util.CMSException;
import com.caoxianfei.cms.util.CMSResult;

/**
 * @author (caoxianfei) email:(948315767@qq.com)
 * @version v1.0
 * @date 
 *	  创建于:2020年6月5日下午6:43:02
 */
@RequestMapping("passport")
@Controller
public class PassportController {

	@Autowired
	private UserService userService;
	
	/**
	 * 
	 * @Title: reg
	 * @Description: 去注册
	 * @return
	 * @return: String
	 */
	@GetMapping("reg")
	public String reg() {

		return "passport/reg";
	}
	
	@ResponseBody
	@PostMapping("reg")
	public CMSResult<User> reg(User user){
		CMSResult<User> result = new CMSResult<User>();
		
		try {
			userService.insert(user);
			result.setCode(200);
			result.setMsg("注册成功!");
		}catch (CMSException e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setCode(500);
			result.setMsg("注册失败!" + e.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setCode(500);
			result.setMsg("注册失败,请联系管理员!");
		}
		
		
		return  result;
	}
	
	
	@RequestMapping("login")
	public String login(Model model,String username) {  //给 用户名一个默认为空字符串的值，单独进入登录页时，不显示注册成功
			model.addAttribute("username", username);		
		return "passport/login";
	}
	
	
	/**
	 * 
	 * @Title: checkusername 
	 * @Description: 检查用户是否已经存在
	 * @param username
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@RequestMapping("checkusername")
	public boolean checkusername(String username) {
		
		return userService.selectByName(username)==null;
		
	}
	
	
	
	@ResponseBody
	@RequestMapping("loginto")
	public CMSResult<User> loginto(User user,HttpSession session) {
		CMSResult<User> result = new CMSResult<User>();
		
		try {
		
			User u = userService.loginto(user);
		
			if(u.getRole()==0) {
			session.setAttribute("user", u);}
			else {
				session.setAttribute("admin", u);}
			result.setCode(200);
			result.setMsg("登陆成功!");
			
		}catch (CMSException e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setCode(500);
			result.setMsg("登录失败!" + e.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setCode(500);
			result.setMsg("登录失败,请联系管理员!");
		}
		return result;
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
