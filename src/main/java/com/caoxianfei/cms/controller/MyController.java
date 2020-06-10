/**
 * 
 */
package com.caoxianfei.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.caoxianfei.cms.domain.Article;
import com.caoxianfei.cms.domain.Category;
import com.caoxianfei.cms.domain.Channel;
import com.caoxianfei.cms.domain.User;
import com.caoxianfei.cms.service.ArticleService;
import com.caoxianfei.cms.service.ChannelService;
import com.caoxianfei.utils.RandomUtil;
import com.github.pagehelper.PageInfo;

/**
 * @author (caoxianfei) email:(948315767@qq.com)
 * @version v1.0
 * @date 创建于:2020年4月28日下午4:03:13
 */
@Controller
@RequestMapping("/my")
public class MyController {

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ChannelService channelService;
	
	@RequestMapping(value= {"index","","/"})
	public String index() {
		return "my/index";
	}
	/**
	 * 
	 *	 功能:    查询多个文章   文章列表
	 *	@return :
	 *
	 */
	@RequestMapping("articles")
	public String articles(Model model,Article article,@RequestParam(defaultValue = "1")Integer pageNum,@RequestParam(defaultValue = "5")Integer pageSize
			,HttpSession session) {
		User user = (User)session.getAttribute("user");
		article.setUserId(user.getId());
		PageInfo<Article> info = articleService.selects(article, pageNum, pageSize);
		model.addAttribute("info", info);
		return "my/articles";
	}
	/**
	 * 
	 *	 功能:回显单个文章内容 
	 *	@return :
	 *
	 */
	@ResponseBody
	@RequestMapping("article")
	public Article article(Integer id) {
		
		return articleService.select(id);
	}
	
	
	@GetMapping("publish")
	public String publish() {
		return "my/publish";
	}
	@ResponseBody
	@PostMapping("publish")
	public int publish(Article article ,MultipartFile file,HttpSession session) {
		if(null != file && !file.isEmpty()) {
			String path = "G:\\img";
			String lastName = RandomUtil.uuid() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")) ;
			File f = new File(path, lastName);
			try {
				file.transferTo(f);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			article.setPicture("/img/"+lastName);
		}
		User user = (User)session.getAttribute("user");
		article.setCreated(new Date());
		article.setUpdated(new Date());
		article.setUserId(user.getId());
		article.setHits(0);
		article.setHot(0);
		article.setStatus(0);
		article.setDeleted(0);
		article.setContentType(0);
		return articleService.insert(article);
	}
	
	@ResponseBody
	@GetMapping("channels")
	public List<Channel> channels(){
		
		return channelService.Channels();
	}
	/**
	 * 
	 *	 功能:   内容改变查询分类
	 *	@return :
	 *
	 */

	@ResponseBody
	@GetMapping("categorys")
	public List<Category> categorys(Integer channelId){
		return channelService.getCategoryList(channelId);
	}
	
	

}
