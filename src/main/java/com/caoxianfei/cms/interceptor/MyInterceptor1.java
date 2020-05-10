package com.caoxianfei.cms.interceptor;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.caoxianfei.cms.domain.User;

public class MyInterceptor1 implements HandlerInterceptor {
	
	  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj)
				throws Exception {
		
		  Boolean  flag = false;
		  //获取请求地址
		  String servletPath = request.getServletPath();
		  HttpSession session = request.getSession();
		 User user= (User) session.getAttribute("loginu");
		 if(user !=null) {  //判断用户是否为空
			 if(!servletPath.contains("/admin")) { //判断是否为只有管理员才能进入的地址
				 flag = true;	
			 }else{
				  flag = user.getRole()==1?true:false;  //管理员的地址，校验登录人是否为管理员，是返回true，不是返回false
			 }
		 }
		 if(!flag) { //是否要去跳转
			 request.setAttribute("url", "/user/to/login.do");
			  request.getRequestDispatcher("/index.do").forward(request, response);
		 }
		  return flag;
		}
}
