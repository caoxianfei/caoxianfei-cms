package com.caoxianfei.cms.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caoxianfei.cms.domain.Article;
import com.caoxianfei.cms.domain.Category;
import com.caoxianfei.cms.domain.Channel;
import com.caoxianfei.cms.domain.User;
import com.caoxianfei.cms.service.ArticleService;
import com.caoxianfei.cms.service.ChannelService;
import com.caoxianfei.cms.service.UserService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("admin")
public class AdminConteroller {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ChannelService channelService;
	
	/**
	 * 
	 *   说明：跳转页面
	 * @author  caoxianfei
	 * @version v1.0
	 *  创建于：2020年5月6日
	 */
	@RequestMapping("index.do")
	public String index() {
		return "admin/index";
	}
	
	/**
	 * 
	 *   说明：文章列表
	 * @author  caoxianfei
	 * @version v1.0
	 *  创建于：2020年5月6日
	 */
	@RequestMapping("getArticleList.do")
	public String  getArticleList(Model model,Article article,@RequestParam(defaultValue="1")Integer pageNum) {
		PageInfo<Article> info = articleService.getArticleList(article,pageNum);
		model.addAttribute("info", info);
		model.addAttribute("article", article);
		return "admin/article";
	}
	
	
    /**
     * 
     *   说明：文章详情
     * @author  caoxianfei
     * @version v1.0
     *  创建于：2020年5月6日
     */
	@RequestMapping("/getArticleById.do")
	@ResponseBody
	public Article getArticleById(Integer articleId) {
		return articleService.getArticleById(articleId);
	}
	
	/**
	 * 
	 *   说明：审核文章and热点控制
	 * @author  caoxianfei
	 * @version v1.0
	 *  创建于：2020年5月6日
	 */
	@RequestMapping("/update.do")
	@ResponseBody
	public Boolean  update(Article article) {
		return  articleService.update(article);
	}
	
	

	
	@RequestMapping("/toIndex.do")
	public String toIndex(Model model,Integer cid) {
		List<Channel> channels = channelService.getChannelList();
		model.addAttribute("channels", channels);
		List<Category> categoryList = channelService.getCategoryList(cid);
		model.addAttribute("categorys", categoryList);
		model.addAttribute("cid", cid);
		return "index/index";
	}
	
	/**
	 * 
	 *   说明：查询所有用户
	 * @author  caoxianfei
	 * @version v1.0
	 *  创建于：2020年5月6日
	 */
	@RequestMapping("getUserList.do")
	public String getUserList(Model model,User user,@RequestParam(defaultValue="1")Integer pageNum) {
		PageInfo<User> info =   userService.getUserList(user,pageNum);
		model.addAttribute("info", info);
		model.addAttribute("user", user);
		return "admin/user";
	}
	
	/**
	 * 
	 *   说明：修改用户状态
	 * @author  caoxianfei
	 * @version v1.0
	 *  创建于：2020年5月6日
	 */
	@RequestMapping("/user/update.do")
	@ResponseBody
	public Boolean updateUser(User user) {
		return userService.updateLocked(user);
	}
}
