/**
 * 
 */
package com.caoxianfei.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caoxianfei.cms.domain.Article;
import com.caoxianfei.cms.domain.User;
import com.caoxianfei.cms.service.ArticleService;
import com.caoxianfei.cms.service.UserService;
import com.caoxianfei.cms.vo.UserVO;
import com.github.pagehelper.PageInfo;

/**
 * @author (caoxianfei) email:(948315767@qq.com)
 * @version v1.0
 * @date 
 *	  创建于:2020年6月1日下午7:48:35
 */
@RequestMapping("admin")
@Controller
public class AdminController {

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = {"","/","index"})
	public String index() {
		return "admin/index";
	}
	
	
	@RequestMapping("articles")
	public String articles(Model model ,Article article ,@RequestParam(defaultValue = "1")Integer pageNum,@RequestParam(defaultValue = "12")Integer pageSize) {
		PageInfo<Article> info = articleService.selects(article, pageNum, pageSize)	;
		model.addAttribute("info", info);
		model.addAttribute("article", article);
		
		return "admin/articles";
	}	

	@ResponseBody
	@RequestMapping("article")
	public Article article(Model model, Integer id) {
		/*
		 * Article select = articleService.select(id); model.addAttribute("sel",select);
		 */
		return articleService.select(id);
	}
	
	
	@ResponseBody
	@PostMapping("updateArticle")
	public  boolean updateArticle(Article article) {
		return articleService.update(article)>0;
	}
	
	
	@RequestMapping("users")
	public String users(Model model, UserVO userVO, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		PageInfo<User> info = userService.selects(userVO, pageNum, pageSize);
		model.addAttribute("info", info);
		model.addAttribute("userVO", userVO);
		return "admin/users";
	}
	
	/**
	 * 
	 * @Title: updateUser 
	 * @Description: 管理用户
	 * @param user
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("updateUser")
	public boolean updateUser(User user) {
		return userService.update(user)>0;
		
	}
}
