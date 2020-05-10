/**
 * 
 */
package com.caoxianfei.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caoxianfei.cms.dao.ArticleMapper;
import com.caoxianfei.cms.domain.Article;
import com.caoxianfei.cms.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author (caoxianfei) email:(948315767@qq.com)
 * @version v1.0
 * @date 
 *	  创建于:2020年4月29日下午7:24:18
 */
@Service
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	private ArticleMapper articleMapper;

	public boolean add(Article article) {
		return articleMapper.add(article);
	}
	

	public PageInfo<Article> getMyArticleList(int pageNum, int uid) {
		PageHelper.startPage(pageNum, 3);
		List<Article> list =  articleMapper.getMyArticleList(uid);
		PageInfo<Article> pageInfo = new PageInfo<Article>(list);
		return pageInfo;
	}

	public Article getArticleById(Integer articleId) {
		return articleMapper.getArticleById(articleId);
	}

	public PageInfo<Article>  getArticleList(Article article, Integer pageNum) {
		PageHelper.startPage(pageNum, 5);
		List<Article>  list = articleMapper.getArticleList(article);
		PageInfo<Article> pageInfo = new PageInfo<Article>(list);
		return pageInfo;
	}

	public Boolean update(Article article) {
		
		return articleMapper.update(article);
	}

	public PageInfo<Article> getByChannelIdAndCategoryId(Integer channelId, Integer categoryId,Integer pageNum) {
		 PageHelper.startPage(pageNum, 5);
		 List<Article> list  = articleMapper.getByChannelIdAndCategoryId(channelId,categoryId);
		return new PageInfo<Article>(list);
	}


	public PageInfo<Article> getHotList(Integer pageNum) {
		 PageHelper.startPage(pageNum, 5);
		 List<Article> hotList = articleMapper.getHotList();
		return new PageInfo<Article>(hotList); 
	}


	public List<Article> getLastArticles() {
		// TODO Auto-generated method stub
		return articleMapper.getLastArticles();
	}
}
