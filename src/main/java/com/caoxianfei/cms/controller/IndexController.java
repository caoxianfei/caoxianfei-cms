package com.caoxianfei.cms.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.caoxianfei.cms.domain.Article;
import com.caoxianfei.cms.domain.Category;
import com.caoxianfei.cms.domain.Channel;
import com.caoxianfei.cms.domain.Slide;
import com.caoxianfei.cms.service.ArticleService;
import com.caoxianfei.cms.service.ChannelService;
import com.caoxianfei.cms.service.SlideService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

@Controller
public class IndexController {
	
	@Autowired
	private ChannelService channelService;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private SlideService slideService;
	
	@RequestMapping("/index.do")
	public String index(Model model,Integer channelId,Integer categoryId,@RequestParam(defaultValue="1")Integer pageNum,HttpServletRequest request) {
		//查询菜单 
				List<Channel>  list = channelService.getChannelList();
				model.addAttribute("channels", list);
				//通过菜单id查询分类
				List<Category> categorys = channelService.getCategoryList(channelId);
				model.addAttribute("categorys", categorys);
				
				//通过菜单id和分类id查询出所有文章
				     PageInfo<Article> info   =  new PageInfo<Article>();
					  if(channelId !=null) {
						  info =  articleService.getByChannelIdAndCategoryId(channelId,categoryId,pageNum);
					  }
				  
					if(channelId == null) {//查询广告列表 及热点文章	
						List<Slide>  slides = slideService.getList();
						model.addAttribute("slides", slides);
						//查询热点文章
						info=articleService.getHotList(pageNum);
					}	
			
					//查询最近的5篇文章用于右侧显示
					List<Article>  lasts = articleService.getLastArticles();
					model.addAttribute("lasts", lasts);
						
					model.addAttribute("info", info);
					model.addAttribute("channelId", channelId);
					model.addAttribute("categoryId", categoryId);
				
				//拦截器要跳转的地址
				model.addAttribute("url", request.getAttribute("url"));
				
				return "index/index";
	}
	
	@RequestMapping("/detail.do")
	public String detail(Integer id,Model model) {
		Article article = articleService.getArticleById(id);
		model.addAttribute("article", article);
		return "index/article";
		
	}

}
