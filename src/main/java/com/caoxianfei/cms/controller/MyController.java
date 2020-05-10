/**
 * 
 */
package com.caoxianfei.cms.controller;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.caoxianfei.cms.domain.Article;
import com.caoxianfei.cms.domain.User;
import com.caoxianfei.cms.service.ArticleService;
import com.github.pagehelper.PageInfo;

/**
 * @author (caoxianfei) email:(948315767@qq.com)
 * @version v1.0
 * @date 
 *	  创建于:2020年4月28日下午4:03:13
 */
@Controller
@RequestMapping("/my")
public class MyController {

	
	@Autowired
	private  ArticleService  articleService;
	
	/**
	 * 
	 *   说明：跳转demo页面
	 * @author  caoxianfei
	 * @version v1.0
	 *  创建于：2020年4月28日
	 */
	@RequestMapping("/toDemo.do")
	public String toDemo() {
		return "my/demo2";
	}
	
	/**
	 * 
	 *   说明：跳转index页面
	 * @author  caoxianfei
	 * @version v1.0
	 *  创建于：2020年4月28日
	 */
	@RequestMapping("/index.do")
	public String index() {
		return "my/index";
	}

	
	/**
	 * 
	 *   说明：  我的文章
	 * @author  caoxianfei
	 * @version v1.0
	 *  创建于：2020年4月28日
	 */
	@RequestMapping("/article.do")
	public String article(Model model,@RequestParam(defaultValue="1")int pageNum,HttpSession session) {
		// 从session中获取登录人
		User user = (User) session.getAttribute("loginu");
		int userId=user.getId();
		PageInfo<Article> pageInfo = articleService.getMyArticleList(pageNum,userId);
		model.addAttribute("info", pageInfo);
		return "my/article";
	}
	
	
	/**
	 * 
	 *   说明：跳转publish页面
	 * @author  caoxianfei
	 * @version v1.0
	 *  创建于：2020年4月28日
	 */
	@RequestMapping("/publish.do")
	public String publish() {
		return "my/publish";
	}
	
	
	
	/**
	 * 
	 *   说明：跳转publish页面
	 * @author caoxianfei
	 * @version v1.0
	 *  创建于：2020年4月28日  .text  .doc
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("/publish/article.do")
	@ResponseBody
	public boolean publishArticle(Article article,MultipartFile file,HttpSession session) throws Exception {
		if(file !=null && !file.isEmpty()) {
			 String paht="G:\\img";  //图片存放位置  注意在判pom.xml中配置虚拟路径
			 String fileName = file.getOriginalFilename(); //获取上传图片的名称  
			 //abc.fa.png
			 String fianlName= UUID.randomUUID()+ fileName.substring(fileName.lastIndexOf(".")); //给图片重新命名
			  File file2 = new File(paht, fianlName);
			  file.transferTo(file2);
			  article.setPicture("/img/"+fianlName);
		}
		article.setCommentNum(0);
		article.setContentType(0);
		article.setCreated(new Date());
		article.setDeleted(0);
		article.setHits(0);
		article.setHot(1);
		article.setStatus(0);
		article.setSummary("摘要");
		article.setUpdated(new Date());
		// 从session中获取登录人
		User user = (User) session.getAttribute("loginu");
		article.setUserId(user.getId());
		
		return articleService.add(article);
	}
	
	
	
	@RequestMapping("/getArticleById.do")
	@ResponseBody
	public Article getArticleById(Integer articleId) {
		return articleService.getArticleById(articleId);
	}
	
}
