package com.caoxianfei.cms.interceptor;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.web.servlet.HandlerInterceptor;

import com.caoxianfei.cms.domain.User;

public class MyInterceptor implements HandlerInterceptor {
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	
		System.out.println("请求了拦截器"); //个人中心  管理员中心
		
		//获取请求地址，用于判断是否请求管理员地址
		String servletPath = request.getServletPath();
		System.out.println(servletPath);
		
		//先获取session
		HttpSession session = request.getSession();
		
		 //获取登陆人信息
		User user = (User) session.getAttribute("loginu");
		session.removeAttribute("adminMsg");
		if(!servletPath.contains("/admin")) { //走个人中心模块
			if(user !=null) {//用户已经登陆
				return true;
			}
		}else { //走管理员中心,要判断登陆人是否是管理员
			if(user !=null && user.getRole() ==1) {
				return  true;
			}
			session.setAttribute("adminMsg", "请用管理员账号登陆！");
		}
		request.setAttribute("url","/user/to/login.do");
		request.getRequestDispatcher("/index.do").forward(request, response);
		return false;
	}

}
