/**
 * 
 */
package com.caoxianfei.cms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caoxianfei.cms.domain.Article;
import com.caoxianfei.cms.domain.Category;
import com.caoxianfei.cms.domain.Channel;
import com.caoxianfei.cms.domain.Comments;
import com.caoxianfei.cms.domain.Slide;
import com.caoxianfei.cms.domain.User;
import com.caoxianfei.cms.service.ArticleService;
import com.caoxianfei.cms.service.ChannelService;
import com.caoxianfei.cms.service.CommentsService;
import com.caoxianfei.cms.service.SlideService;
import com.caoxianfei.cms.util.CMSResult;
import com.caoxianfei.utils.DateUtil;
import com.github.pagehelper.PageInfo;

/**
 * @author (caoxianfei) email:(948315767@qq.com)
 * @version v1.0
 * @date 
 *	  创建于:2020年6月4日上午10:28:30
 */

@Controller
public class IndexController {

	@Autowired
	private ChannelService channelService;
	
	@Autowired
	private SlideService slideService;
	
	@Autowired
	private CommentsService commentsService;
	
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping(value = {"","/","index"})
	public String index(Model model,Article article,@RequestParam(defaultValue = "1")Integer pageNum
			,@RequestParam(defaultValue = "3")Integer pageSize,String key) {
		article.setStatus(1);
		model.addAttribute("article", article);
		List<Channel> channels = channelService.Channels();
		model.addAttribute("channels", channels);
		
		//2如果栏目id不为空，则根据栏目查询分类
				if(article.getChannelId()!=null) {
					//2.1查询栏目下分类
					List<Category> categorys = channelService.getCategoryList(article.getChannelId());
					model.addAttribute("categorys", categorys);
					//2.1查询栏目下的文章
					PageInfo<Article> info = articleService.selects(article, pageNum, pageSize);
					model.addAttribute("info", info);
				}
				
				//3 .如果未点栏目或者点击的是热点栏目，则显示热点文章
				if(article.getChannelId()==null) {
			if (key == null || "".equals(key.trim())) {

//				// 3.1查询热点文章    修改前代码
//				article.setHot(1);// 热点文章
//				PageInfo<Article> page = articleService.selects(article, pageNum, pageSize);
//				model.addAttribute("info", page);
				
				//3.1查询热点文章
				article.setHot(1);//热点文章
				PageInfo<Article> page = articleService.selectHot(article, pageNum, pageSize);
				model.addAttribute("info", page);
			} else {

				// 从es中查询数据
				PageInfo<Article> page = articleService.selectFromES(pageNum, pageSize, key);
				model.addAttribute("info", page);

				model.addAttribute("key", key);
			}
					

					//3.2查询广告
					List<Slide> slides = slideService.getList();
					model.addAttribute("slides", slides);
				}
				
				//4 24小时热文  -只显示4条
				//4.1获取昨日的日期
				Date startDate = DateUtil.addDays(-1, new Date());
				article.setCreated(startDate);
				//4.2 设置热点文章
				article.setHot(1);
				PageInfo<Article> hot24Articles = articleService.selects(article, 1, 4);
				model.addAttribute("hot24Articles", hot24Articles);
				
				//审核后的条件
				Article last = new Article();
				last.setStatus(1);
				//查询最新的5条数据
				PageInfo<Article> lastInfo = articleService.selectLast(last, 1, 5);	
				model.addAttribute("lastInfo", lastInfo);
				
		return "index/index";
	}
	
	
	@RequestMapping("detail")
	public String detail(Model model,Integer id) {
		Article article = articleService.select(id);
		model.addAttribute("article", article);
		PageInfo<Comments> info = commentsService.selectByIdList(id);
		model.addAttribute("info", info);
		return "index/article";    //使查询到的文章 跳转另一个页面显示
		
	}

	
	@ResponseBody
	@RequestMapping("insert")
	public CMSResult<Comments> insert (Comments comments,HttpSession session) {
		CMSResult<Comments> result = new CMSResult<Comments>();
		
		User user = (User)session.getAttribute("user");
		if(null==user) {
			result.setCode(500);
			result.setMsg("请登录后再评论");
			return result;
		}
		comments.setUserId(user.getId());
		comments.setCreated(new Date());
		commentsService.insert(comments);
		result.setCode(200);
		result.setMsg("评论成功!");
	return result;
	}
	
}
