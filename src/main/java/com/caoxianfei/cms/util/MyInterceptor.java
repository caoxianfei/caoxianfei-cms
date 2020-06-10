package com.caoxianfei.cms.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * MyInterceptor 个人中心拦截器 charles 2020年6月6日 上午11:02:26
 */
public class MyInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//1.如果已经登录，session有对应的值，则不拦截
		//false：如果request中有session对象，则返回。如果没有则返回null
		//true:如果request中有session对象，则返回。如果没有则创建一个新的session 对象再返回
		HttpSession session = request.getSession(false);
		if(null!=session &&null!=session.getAttribute("user")) {
				return true;//不拦截
		}
		//如果没有登录则把请求转发到登录页面
		request.setAttribute("msg", "请登录后再试...");
		request.getRequestDispatcher("/WEB-INF/passport/login.jsp").forward(request, response);
		
		return false;
	}
}
